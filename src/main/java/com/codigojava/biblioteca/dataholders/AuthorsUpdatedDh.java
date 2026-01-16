package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorsUpdatedDh extends AuthorsBaseDh {

    @NotNull(message = "The authorId cannot be null")
    @Min(value = 1, message = "The authorId must be greater than 0")
    private Integer authorId;
}
