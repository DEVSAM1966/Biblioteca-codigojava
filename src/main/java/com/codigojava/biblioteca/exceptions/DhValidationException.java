package com.codigojava.biblioteca.exceptions;

public class DhValidationException extends RuntimeException{

  private final String field;

  public DhValidationException(String field, String message) {
    super(message);
    this.field = field;
  }

  public String getField() {
    return field;
  }

}
