package com.codigojava.biblioteca.dtos;

import com.codigojava.biblioteca.entities.RoleEnum;

import java.time.LocalDate;

public record AuthResponse(
        UserSummaryDto user,
        String authorization
) {


}
