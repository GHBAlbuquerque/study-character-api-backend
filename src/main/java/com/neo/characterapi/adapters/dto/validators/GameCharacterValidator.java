package com.neo.characterapi.adapters.dto.validators;

import com.neo.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.neo.characterapi.adapters.dto.validators.annotations.ValidGameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class GameCharacterValidator implements ConstraintValidator<ValidGameCharacter, CreateGameCharacterDto> {

    private static final String REGEX = "^[A-Za-z_]+$";
    private static final Integer MIN_LENGTH = 4;
    private static final Integer MAX_LENGTH = 15;
    private static final List<String> JOB_NAMES = JobType.names();

    @Override
    public boolean isValid(CreateGameCharacterDto request, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        final Boolean isNameValid = validateName(request.name(), context);
        final Boolean isJobValid = validateJob(request.job(), context);

        return isNameValid && isJobValid;
    }

    private Boolean validateName(String name, ConstraintValidatorContext context) {
        if (Objects.isNull(name)) return false;

        if (!name.matches(REGEX)) {
            context.buildConstraintViolationWithTemplate("Name must contain only letters or '_' (underscore) characters.").addConstraintViolation();
        }

        if (name.length() < MIN_LENGTH) {
            context.buildConstraintViolationWithTemplate(String.format("Name should have at least %s characters.", MIN_LENGTH)).addConstraintViolation();
            return false;
        }

        if (name.length() > MAX_LENGTH) {
            context.buildConstraintViolationWithTemplate(String.format("Name should have less than %s characters.", MAX_LENGTH)).addConstraintViolation();
            return false;
        }

        return true;
    }

    private Boolean validateJob(String job, ConstraintValidatorContext context) {
        if (!JOB_NAMES.contains(job)) {
            context.buildConstraintViolationWithTemplate(String.format("Job must be one of: %s", JOB_NAMES)).addConstraintViolation();
            return false;
        }

        return true;
    }

}
