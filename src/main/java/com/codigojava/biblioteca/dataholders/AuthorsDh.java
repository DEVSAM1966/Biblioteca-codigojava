package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorsDh {

    @NotBlank(message = "The author name cannot be empty")
    @Size(max = 100, message = "The author name must not exceed 100 characters")
    private String nameAuthor;

}
