package com.neo.characterapi.adapters.dto.validators.annotations;

import com.neo.characterapi.adapters.dto.validators.GameCharacterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GameCharacterValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGameCharacter {
    String message() default "Invalid name.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
