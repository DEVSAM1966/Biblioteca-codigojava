package com.codigojava.biblioteca.dataholders;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class PublishersBaseDh {

    private static final String TEXT_PATTERN = "^[\\p{L}0-9\\s\\.,'’\\-\u00B7]*$";

    @NotBlank(message = "Publisher name is mandatory")
    @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "Publisher name contains invalid characters")
    private String namePublisher;

    @Size(max = 100, message = "Address cannot exceed 100 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "Address contains invalid characters")
    private String address;

    @Size(max = 40, message = "City cannot exceed 40 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "City contains invalid characters")
    private String city;

    @Size(max = 30, message = "Province cannot exceed 30 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "Province contains invalid characters")
    private String province;
     //solo numeros
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    @Pattern(regexp = "^\\d+$", message = "Postal code must contain only numbers")
    private String postalCode;

    @Size(max = 30, message = "Country cannot exceed 30 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "Country contains invalid characters")
    private String country;

    // solo numeros y algunos caracteres especiales pero no letras 
    @Size(max = 16, message = "Phone number cannot exceed 16 characters")
    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Phone number contains invalid characters (letters are not allowed)")
    private String phone;

    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;
}