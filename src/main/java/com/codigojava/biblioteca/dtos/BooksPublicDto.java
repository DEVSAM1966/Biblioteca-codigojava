package com.codigojava.biblioteca.dtos;

public record BooksPublicDto(

        String isbn,

        String title,

        String language,

        String bookCover,

        String nameAuthor,

        String nameCategory,

        String subtopicCategory,

        String namePublisher
) {}
