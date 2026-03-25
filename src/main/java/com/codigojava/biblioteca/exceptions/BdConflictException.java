package com.codigojava.biblioteca.exceptions;


public class BdConflictException extends RuntimeException {
    public BdConflictException(String message) {
        super(message);
    }
}
