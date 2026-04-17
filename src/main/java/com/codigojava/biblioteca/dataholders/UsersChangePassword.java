package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsersChangePassword(
        @NotBlank(message = "User DNI is mandatory")
        @Size(max = 20, message = "User DNI cannot exceed 20 characters")
        String dni,

        @NotBlank(message = "User email is mandatory")
        @Size(max = 120, message = "User email cannot exceed 120 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format")
        String email,

        @NotBlank(message = "User phone is mandatory")
        @Size(max = 16, message = "User phone cannot exceed 16 characters")
        @Pattern(
                regexp = "^\\+?[0-9\\s\\-()]{6,20}$",
                message = "Invalid phone number format")
        String phone,

        @NotBlank(message = "User password is mandatory")
        @Size(min = 8, max = 25, message = "User password cannot exceed 25 characters")
        @Pattern(
                regexp = "^(?=(?:.*[A-Z]){2,})(?=(?:.*[a-z]){2,})(?=.*\\d).{8,25}$",
                message = "Password must contain at least 2 uppercase letters, 2 lowercase letters, 1 number, and be 8–25 characters long")
        String password,

        @NotBlank(message = "User password Repeat is mandatory")
        @Size(min = 8, max = 25, message = "User password cannot exceed 25 characters")
        @Pattern(
                regexp = "^(?=(?:.*[A-Z]){2,})(?=(?:.*[a-z]){2,})(?=.*\\d).{8,25}$",
                message = "Password must contain at least 2 uppercase letters, 2 lowercase letters, 1 number, and be 8–25 characters long")
        String passwordRepeat

) {
}
