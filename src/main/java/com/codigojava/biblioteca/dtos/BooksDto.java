package com.codigojava.biblioteca.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BooksDto {

    private String isbn;

    private String title;

    private String language;

    private Integer pages;

    private String summary;

    private LocalDate editionDate;

    private String bookCover;

    private String bookFile;

    private Integer authorId;

    private String authors;

    private Integer publisherId;

    private Integer categoryId;

}
