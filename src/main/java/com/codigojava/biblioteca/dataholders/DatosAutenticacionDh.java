package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionDh(
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "password is required")
        String password
) {
}
