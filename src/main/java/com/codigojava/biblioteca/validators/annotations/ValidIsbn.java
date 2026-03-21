package com.codigojava.biblioteca.validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = com.codigojava.biblioteca.validators.ValidIsbnValidator.class)
public @interface ValidIsbn {

    String message() default "The ISBN must contain between 10 and 13 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

