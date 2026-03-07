package com.codigojava.biblioteca.dtos;

import java.time.LocalDate;

public record LoansDto(

    Integer loanId,

    LocalDate loanDate,

    LocalDate returnDate,

    Integer userId,

    String isbn

)
{}
