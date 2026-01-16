package com.codigojava.biblioteca.dataholders;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoriesUpdatedDh extends CategoriesBaseDh {

    @NotNull(message = "The categoryId cannot be null")
    @Min(value = 1, message = "The categoryId must be greater than 0")
    private Integer categoryId;

}
