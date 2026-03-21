package com.codigojava.biblioteca.dtos;

import java.time.LocalDate;

public record BooksPublicIsbnDto(

        String isbn,

        String title,

        String language,

        Integer pages,

        String summary,

        LocalDate editionDate,

        String bookCover,

        String authors,

        String nameAuthor,

        String nameCategory,

        String subtopicCategory,

        String namePublisher
) {}
