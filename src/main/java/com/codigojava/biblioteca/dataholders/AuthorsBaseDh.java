package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class AuthorsBaseDh {

    @NotBlank(message = "The author name cannot be empty")
    @Size(min = 4, max = 100, message = "The author name must be between 4 and 100 characters")
    @Pattern(
            regexp = "^[\\p{L} .'’\\-]+$",
            message = "The author name can only contain letters, spaces, dots, hyphens and apostrophes"
    )
    private String nameAuthor;

}
