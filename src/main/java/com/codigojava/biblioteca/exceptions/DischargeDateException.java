package com.codigojava.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DischargeDateException extends RuntimeException {

    public DischargeDateException(String message) {

        super(message);
    }

}
