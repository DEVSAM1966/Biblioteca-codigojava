package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BooksRecordDh(

        @NotBlank(message = "Isbn is required")
        @Size(max = 13, message = "Isbn cannot exceed 13 characters")
        @Pattern(
                regexp = "^(?:\\d{9}[\\dXx]|\\d{13})$",
                message = "Book ISBN must be either 10 digits (last can be X) or 13 digits")
        String isbn,

        @Size(max = 55, message = "Book Title must be at most 55 characters long")
        String title,

        @Min(value = 1, message = "Book Pages must be a number positive")
        Integer pages,

        @Size(max = 255, message = "Book Summary must be at most 255 characters long")
        String summary,

        LocalDate editionDate,

        @Size(max = 20, message = "Language must be at most 20 characters long")
        String language,

        @Size(max = 100, message = "Book Authors must be at most 100 characters long")
        String authors,

        @Min(value = 1, message = "Book Author ID must be a number positive")
        Integer authorId,

        @Min(value = 1, message = "Book Publisher ID must be a number positive")
        Integer publisherId,

        @Min(value = 1, message = "Book Category ID must be a number positive")
        Integer categoryId

) {}
