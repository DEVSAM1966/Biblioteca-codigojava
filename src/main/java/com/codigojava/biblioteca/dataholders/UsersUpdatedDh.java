package com.codigojava.biblioteca.dataholders;

import com.codigojava.biblioteca.entities.RoleEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsersUpdatedDh extends UsersBaseDh {

    @NotNull(message = "The userId cannot be null")
    @Min(value = 1, message = "The userId must be greater than 0")
    private Integer userId;

    private Boolean userDrop;

    @Min(value = 0, message = "The daysDisciplinary must be equal or greater than 0")
    private Integer daysDisciplinary;

    private RoleEnum role;

}
