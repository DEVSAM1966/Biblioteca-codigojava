package com.codigojava.biblioteca.dataholders;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record LoansUpdateRecordDh(

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate loanDate,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate returnDate
        )
{}
