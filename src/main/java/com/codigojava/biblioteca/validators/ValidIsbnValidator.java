package com.codigojava.biblioteca.validators;

import com.codigojava.biblioteca.validators.annotations.ValidIsbn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidIsbnValidator implements ConstraintValidator<ValidIsbn, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        int length = value.length();
        return length >= 10 && length <= 13;
    }
}

