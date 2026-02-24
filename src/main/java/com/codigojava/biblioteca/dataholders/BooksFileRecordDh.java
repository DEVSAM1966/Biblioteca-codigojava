package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BooksFileRecordDh(

        @NotBlank(message = "Isbn is required")
        @Size(max = 13, message = "Isbn cannot exceed 13 characters")
        @Pattern(
                regexp = "^(?:\\d{9}[\\dXx]|\\d{13})$",
                message = "Book ISBN must be either 10 digits (last can be X) or 13 digits")
        String isbn,

        @Size(max = 255, message = "Book Cover path must be at most 255 characters long")
        String bookCover,

        @Size(max = 255, message = "Book File path must be at most 255 characters long")
        String bookFile

) {}
