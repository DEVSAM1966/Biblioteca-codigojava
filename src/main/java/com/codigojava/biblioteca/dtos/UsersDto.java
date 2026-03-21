package com.codigojava.biblioteca.dtos;

import com.codigojava.biblioteca.entities.RoleEnum;
import java.time.LocalDate;

public record UsersDto(

        Integer userId,

        String fullname,

        String dni,

        String address,

        String city,

        String province,

        String postalCode,

        String country,

        String phone,

        String email,

        String password,

        LocalDate registrationDate,

        boolean userDrop,

        Integer daysDisciplinary,

        RoleEnum role
) {}
