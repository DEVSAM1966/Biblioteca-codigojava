package com.codigojava.biblioteca.dtos;

import java.time.LocalDate;

public record HistoriesDto (
        Integer historyId,

        LocalDate dateFeedback,

        String feedback,

        Integer loanId

){}
