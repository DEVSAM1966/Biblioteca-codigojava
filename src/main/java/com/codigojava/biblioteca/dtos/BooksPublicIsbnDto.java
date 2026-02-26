package com.codigojava.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BooksPublicIsbnDto {

    private String isbn;

    private String title;

    private String language;

    private Integer pages;

    private String summary;

    private LocalDate editionDate;

    private String bookCover;

    private String authors;

    private String nameAuthor;

    private String nameCategory;

    private String subtopicCategory;

    private String namePublisher;

}
