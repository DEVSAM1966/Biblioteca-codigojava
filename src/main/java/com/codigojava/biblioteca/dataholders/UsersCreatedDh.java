package com.codigojava.biblioteca.dataholders;

import com.codigojava.biblioteca.entities.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsersCreatedDh extends UsersBaseDh{
    @NotBlank(message = "User full name is mandatory")
    private String fullname;

    @NotBlank(message = "User DNI is mandatory")
    private String dni;

    @NotBlank(message = "User phone is mandatory")
    private String phone;

    @NotBlank(message = "User email is mandatory")
    private String email;

    @NotBlank(message = "User password is mandatory")
    private String password;

    private RoleEnum role;

}
