package com.codigojava.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControlAdvice {

    @ExceptionHandler(BdNotFoundException.class)
    public ResponseEntity<Object> bdNotFoundHandler(Exception e) {

        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .description("(Exception) - The object in BD not found")
                .date(java.time.LocalDate.now())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BdNotSaveException.class)
    public ResponseEntity<Object> bdNotSaveHandler(Exception e) {

        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .description("(Exception) - The object in BD can´t be saved")
                .date(java.time.LocalDate.now())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BdInternalException.class)
    public ResponseEntity<Object> bdInternalHandler(Exception e) {

        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .description("(Exception) - Internal error occurred in database")
                .date(java.time.LocalDate.now())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()) );
        ApiError apiError = ApiError.builder()
                .message("Validation failed")
                .description("Some fields are invalid")
                .date(LocalDate.now()) .build();
        Map<String, Object> body = new HashMap<>();
        body.put("error", apiError);
        body.put("validationErrors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
