package com.codigojava.biblioteca.validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = com.codigojava.biblioteca.validators.PositiveIdValidator.class)
public @interface PositiveId {

    String message() default "The id must be a positive integer greater than 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

