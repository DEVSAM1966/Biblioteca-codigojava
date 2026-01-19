package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public abstract class UsersBaseDh {

    private static final String TEXT_PATTERN = "^[\\p{L}0-9\\s\\.,'’\\-\\u00B7]*$";
    private static final String PHONE_PATTERN = "^\\+?[0-9\\s\\-()]{6,20}$";
    private static final String PASSWORD_PATTERN = "^(?=(?:.*[A-Z]){2,})(?=(?:.*[a-z]){2,})(?=.*\\d).{8,25}$";
    private static final String POSTAL_PATTERN = "^[A-Za-z0-9\\s\\-]{3,12}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @NotBlank(message = "User full name is mandatory")
    @Size(max = 120, message = "User full name cannot exceed 120 characters")
    @Pattern(regexp = TEXT_PATTERN, message = "User full name contains invalid characters")
    private String fullname;

    @NotBlank(message = "User DNI is mandatory")
    @Size(max = 20, message = "User DNI cannot exceed 20 characters")
    private String dni;


    @Size(max = 100, message = "User address cannot exceed 100 characters")
    private String address;

    @Size(max = 40, message = "User city cannot exceed 40 characters")
    private String city;

    @Size(max = 30, message = "User province cannot exceed 30 characters")
    private String province;

    @Size(max = 20, message = "User postal code cannot exceed 20 characters")
    @Pattern(regexp = POSTAL_PATTERN, message = "Invalid postal code format")
    private String postalCode;

    @Size(max = 30, message = "User country cannot exceed 30 characters")
    private String country;

    @NotBlank(message = "User phone is mandatory")
    @Size(max = 16, message = "User phone cannot exceed 16 characters")
    @Pattern(regexp = PHONE_PATTERN, message = "Invalid phone number format")
    private String phone;

    @NotBlank(message = "User email is mandatory")
    @Size(max = 120, message = "User email cannot exceed 120 characters")
    @Pattern(regexp = EMAIL_PATTERN, message = "Invalid email format")
    private String email;

    @NotBlank(message = "User password is mandatory")
    @Size(min = 8, max = 25, message = "User password cannot exceed 25 characters")
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain at least 2 uppercase letters, 2 lowercase letters, 1 number, and be 8–25 characters long")
    private String password;

}
