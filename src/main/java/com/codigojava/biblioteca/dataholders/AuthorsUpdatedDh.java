package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.*;

public record AuthorsUpdatedDh(

        @NotNull(message = "The authorId cannot be null")
        @Min(value = 1, message = "The authorId must be greater than 0")
        Integer authorId,

        @NotBlank(message = "The author name cannot be empty")
        @Size(min = 4, max = 100, message = "The author name must be between 4 and 100 characters")
        @Pattern(
                regexp = "^[\\p{L} .'’\\-]+$",
                message = "The author name can only contain letters, spaces, dots, hyphens and apostrophes")
        String nameAuthor

) {}
