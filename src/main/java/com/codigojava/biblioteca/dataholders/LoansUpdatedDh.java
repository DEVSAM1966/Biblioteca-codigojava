package com.codigojava.biblioteca.dataholders;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LoansUpdatedDh(

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate loanDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate returnDate
        )
{}
