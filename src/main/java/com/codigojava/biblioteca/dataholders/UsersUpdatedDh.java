package com.codigojava.biblioteca.dataholders;

import com.codigojava.biblioteca.entities.RoleEnum;
import jakarta.validation.constraints.*;

public record UsersUpdatedDh(

        @NotNull(message = "The userId cannot be null")
        @Min(value = 1, message = "The userId must be greater than 0")
        Integer userId,

        @Size(max = 120, message = "User full name cannot exceed 120 characters")
        @Pattern(
                regexp = "^[\\p{L}0-9\\s\\.,'’\\-\\u00B7]*$",
                message = "User full name contains invalid characters")
        String fullname,

        @Size(max = 20, message = "User DNI cannot exceed 20 characters")
        String dni,

        @Size(max = 100, message = "User address cannot exceed 100 characters")
        String address,

        @Size(max = 40, message = "User city cannot exceed 40 characters")
        String city,

        @Size(max = 30, message = "User province cannot exceed 30 characters")
        String province,

        @Size(max = 20, message = "User postal code cannot exceed 20 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9\\s\\-]{3,12}$",
                message = "Invalid postal code format")
        String postalCode,

        @Size(max = 30, message = "User country cannot exceed 30 characters")
        String country,

        @Size(max = 16, message = "User phone cannot exceed 16 characters")
        @Pattern(
                regexp = "^\\+?[0-9\\s\\-()]{6,20}$",
                message = "Invalid phone number format")
        String phone,

        @Size(max = 120, message = "User email cannot exceed 120 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format")
        String email,

        @Size(min = 8, max = 25, message = "User password cannot exceed 25 characters")
        @Pattern(
                regexp = "^(?=(?:.*[A-Z]){2,})(?=(?:.*[a-z]){2,})(?=.*\\d).{8,25}$",
                message = "Password must contain at least 2 uppercase letters, 2 lowercase letters, 1 number, and be 8–25 characters long")
        String password,

        Boolean userDrop,

        @Min(value = 0, message = "The daysDisciplinary must be equal or greater than 0")
        Integer daysDisciplinary,

        RoleEnum role

) {}
