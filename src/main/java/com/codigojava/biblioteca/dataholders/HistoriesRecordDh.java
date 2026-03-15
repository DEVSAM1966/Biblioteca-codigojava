package com.codigojava.biblioteca.dataholders;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record HistoriesRecordDh(

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateFeedback,

        @Size(max = 255, message = "Feedback cannot exceed 255 characters")
        String feedback,

        @Min(value = 1, message = "Loan id must be a number positive")
        Integer loanId
)
{}
