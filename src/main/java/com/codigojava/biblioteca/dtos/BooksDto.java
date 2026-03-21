package com.codigojava.biblioteca.dtos;

import java.time.LocalDate;

public record BooksDto(
        String isbn,

        String title,

        String language,

        Integer pages,

        String summary,

        LocalDate editionDate,

        String bookCover,

        String bookFile,

        Integer authorId,

        String authors,

        Integer publisherId,

        Integer categoryId
) {}
