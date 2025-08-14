package com.neo.characterapi.application.exceptions;

import com.neo.characterapi.application.exceptions.model.ExceptionDetails;
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
                "The requested resource was not found.",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                new HashMap<>());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {CreateCharacterException.class})
    public ResponseEntity<ExceptionDetails> resourceException(CreateCharacterException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "Couldn't create the character. Try again with different values.",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                new HashMap<>());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidBattleException.class})
    public ResponseEntity<ExceptionDetails> resourceException(InvalidBattleException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "There was a problem with the battle. Try again with different values.",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                new HashMap<>());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtException(Exception ex, WebRequest request) {
        log.error("Uncaught Exception. {}", ex.getMessage());
        log.error("Class: {}", ex.getClass());

        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        final var message = new ExceptionDetails(
                "Internal server error. Please contact the admin.",
                "Unindentified error.",
                status.value(),
                new Date(),
                new HashMap<>());

        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

}
