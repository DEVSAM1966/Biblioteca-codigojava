package com.codigojava.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(DhValidationException.class)
    public ResponseEntity<Object> handleDhValidationException(DhValidationException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put(ex.getField(), ex.getMessage());

        ApiError apiError = ApiError.builder()
            .message("Validation failed")
            .description("Some fields are invalid")
            .date(LocalDate.now())
            .build();

        Map<String, Object> body = new HashMap<>();
        body.put("validationErrors", validationErrors);
        body.put("error", apiError);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex) {

        Map<String, String> validationErrors = new HashMap<>();

        ex.getConstraintViolations().forEach(v -> {
            String field = v.getPropertyPath().toString();
            validationErrors.put(field, v.getMessage());
        });

        ApiError apiError = ApiError.builder()
                .message("Validation failed")
                .description("Some fields are invalid")
                .date(LocalDate.now())
                .build();

        Map<String, Object> body = new HashMap<>();
        body.put("validationErrors", validationErrors);
        body.put("error", apiError);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

        String field = ex.getName(); // normalmente "id"

        Map<String, String> validationErrors = Map.of(
            field, "The value must be a valid integer"
        );

        ApiError apiError = ApiError.builder()
            .message("Validation failed")
            .description("Some fields are invalid")
            .date(LocalDate.now())
            .build();

        Map<String, Object> body = Map.of(
            "validationErrors", validationErrors,
            "error", apiError
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMalformedJson(HttpMessageNotReadableException ex) {

        String message = ex.getMessage();
        String fieldName = null;

        // Intentamos extraer el nombre del campo si aparece en el mensaje
        if (message != null && message.contains("[")) {
            int start = message.lastIndexOf("[\"");
            int end = message.lastIndexOf("\"]");
            if (start != -1 && end != -1 && end > start) {
                fieldName = message.substring(start + 2, end);
            }
        }

        Map<String, String> validationErrors = new HashMap<>();

        if (fieldName != null) {
            validationErrors.put(fieldName, "Invalid JSON value or malformed syntax");
        }

        ApiError apiError = ApiError.builder()
                .message("Malformed JSON")
                .description("The request body contains invalid JSON syntax")
                .date(LocalDate.now())
                .build();

        Map<String, Object> body = new HashMap<>();
        body.put("validationErrors", validationErrors);
        body.put("error", apiError);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BdConflictException.class)
    public ResponseEntity<Object> bdConflictHandler(Exception e) {

        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .description("(Exception) - The object cannot be deleted due to existing associations")
                .date(java.time.LocalDate.now())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }


}
