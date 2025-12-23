package com.codigojava.biblioteca.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BdNotSaveException extends RuntimeException {

    public BdNotSaveException(String message) {

        super(message);
    }

}
