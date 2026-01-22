package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NameValidationGenericDh {

  @Pattern(
      regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ .-]+$",
      message = "The name must contain only letters, spaces, dots or hyphens"
  )
  private String name;
}
