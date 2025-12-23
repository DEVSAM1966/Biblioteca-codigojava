package com.codigojava.biblioteca.exceptions;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    private String message;

    private String description;

    private LocalDate date;
}
