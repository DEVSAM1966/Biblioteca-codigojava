package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriesUpdatedDh {

    @NotNull(message = "The categoryId cannot be null")
    @Min(value = 1, message = "The categoryId must be greater than 0")
    private Integer categoryId;

    @NotBlank(message = "The category name cannot be empty")
    @Size(min = 3, max = 30, message = "The category name must be between 3 and 30 characters")
    @Pattern(
            regexp = "^[\\p{L} .'’\\-]+$",
            message = "The category name can only contain letters, spaces, dots, hyphens and apostrophes"
    )
    private String nameCategory;

    @Size(max = 30, message = "The category name must be between 0 and 30 characters")
    private String subtopicCategory;
}
