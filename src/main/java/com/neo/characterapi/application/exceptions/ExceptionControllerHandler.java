package com.neo.characterapi.application.exceptions;

import com.neo.characterapi.application.exceptions.model.ExceptionDetails;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(ExceptionControllerHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        final var errors = new HashMap<String, List<String>>();

        ex.getBindingResult().getAllErrors().forEach(violation -> {
            String key = violation.getObjectName();
            errors.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(violation.getDefaultMessage());
        });

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "Method Argument Not Valid",
                "Invalid arguments.",
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(value = {CharacterNotFoundException.class})
    public ResponseEntity<ExceptionDetails> resourceException(CharacterNotFoundException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404",
                "The requested resource was not found.",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                null);

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(value = {CreateEntityException.class})
    public ResponseEntity<ExceptionDetails> resourceException(CreateEntityException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "Couldn't create entity on database. Try again with different values.",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getErrors());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }*/


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtException(Exception ex, WebRequest request) {
        log.error("Uncaught Exception. {}", ex.getMessage());
        log.error("Class: {}", ex.getClass());

        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500",
                "Internal server error. Please contact the admin.",
                "Unindentified error.",
                status.value(),
                new Date(),
                null);

        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

}
