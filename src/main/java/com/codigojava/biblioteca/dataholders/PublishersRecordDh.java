package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PublishersRecordDh(

        @NotBlank(message = "Publisher name is mandatory")
        @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
        @Pattern(
                regexp = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$",
                message = "Publisher name contains invalid characters")
        String namePublisher,

        @Size(max = 100, message = "Address cannot exceed 100 characters")
        @Pattern(
                regexp = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$",
                message = "Address contains invalid characters")
        String address,

        @Size(max = 40, message = "City cannot exceed 40 characters")
        @Pattern(
                regexp = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$",
                message = "City contains invalid characters")
        String city,

        @Size(max = 30, message = "Province cannot exceed 30 characters")
        @Pattern(regexp = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$",
                message = "Province contains invalid characters")
        String province,

        // solo numeros
        @Size(max = 20, message = "Postal code cannot exceed 20 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9\\s-]+$",
                message = "Postal code must contain only numbers")
        String postalCode,

        @Size(max = 30, message = "Country cannot exceed 30 characters")
        @Pattern(
                regexp = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$",
                message = "Country contains invalid characters")
        String country,

        // solo numeros y algunos caracteres especiales pero no letras
        @Size(max = 16, message = "Phone number cannot exceed 16 characters")
        @Pattern(
                regexp = "^[0-9+\\-\\s()]{6,16}$",
                message = "Phone number contains invalid characters (letters are not allowed)")
        String phone,

        @Size(max = 255, message = "Notes cannot exceed 255 characters")
        String notes
)
{}
