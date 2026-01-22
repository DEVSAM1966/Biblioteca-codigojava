package com.codigojava.biblioteca.validators;

import com.codigojava.biblioteca.exceptions.DhValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class DhValidator {

  private final Validator validator;

  public DhValidator(Validator validator) {
    this.validator = validator;
  }

  public <T> void validate(T dh) {
    Set<ConstraintViolation<T>> violations = validator.validate(dh);

    if (!violations.isEmpty()) {
      ConstraintViolation<T> v = violations.iterator().next();

      throw new DhValidationException(
          v.getPropertyPath().toString(),
          v.getMessage()
      );
    }
  }

}
