package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoriesUpdatedDh {

    @NotBlank(message = "The category name cannot be empty")
    @Size(min = 3, max = 100, message = "The category name must be between 3 and 30 characters")
    @Pattern(
            regexp = "^[\\p{L} .'’\\-]+$",
            message = "The category name can only contain letters, spaces, dots, hyphens and apostrophes"
    )
    private String nameCategory;

    private String subtopicCategory;
}
