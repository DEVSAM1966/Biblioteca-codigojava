package com.codigojava.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BdNotFoundException extends RuntimeException {

    public BdNotFoundException(String message) {

        super(message);

    }

}
