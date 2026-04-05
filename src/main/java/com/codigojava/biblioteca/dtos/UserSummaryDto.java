package com.codigojava.biblioteca.dtos;

import com.codigojava.biblioteca.entities.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserSummaryDto(
        String fullname,
        LocalDateTime registrationDate,
        RoleEnum role,
        Integer userId,
        boolean userDrop
) {
}
