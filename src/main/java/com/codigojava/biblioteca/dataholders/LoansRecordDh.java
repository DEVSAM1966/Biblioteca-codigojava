package com.codigojava.biblioteca.dataholders;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LoansRecordDh(

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate loansDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate loanReturn,

        @Min(value = 1, message = "User ID must be a number positive")
        Integer userId,

        @NotBlank(message = "Isbn is required")
        @Size(max = 13, message = "Isbn cannot exceed 13 characters")
        @Pattern(
                regexp = "^(?:\\d{9}[\\dXx]|\\d{13})$",
                message = "Book ISBN must be either 10 digits (last can be X) or 13 digits")
        String isbn

)
{}
