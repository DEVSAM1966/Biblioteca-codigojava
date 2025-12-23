package com.codigojava.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
