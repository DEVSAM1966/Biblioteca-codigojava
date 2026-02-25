package com.codigojava.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BooksPublicDto {

    private String isbn;

    private String title;

    private String language;

    private String bookCover;

    private String nameAuthor;

    private String nameCategory;

    private String subtopicCategory;

    private String namePublisher;

}
