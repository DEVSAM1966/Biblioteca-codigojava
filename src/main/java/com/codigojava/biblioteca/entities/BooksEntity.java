package com.codigojava.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BooksEntity {

    @Id
    @Column(name = "isbn", length = 13, nullable = false)
    private String isbn;

    @Column(name = "title", length = 55, nullable = false)
    private String title;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "summary", length = 255)
    private String summary;

    @Column(name = "edition_date")
    private LocalDate editionDate;

    @Column(name = "book_cover", length = 255)
    private String bookCover;

    @Column(name = "book_file", length = 255)
    private String bookFile;

    @Column(name = "language", length = 20)
    private String language;

    @Column(name = "authors", length = 100)
    private String authors;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorsEntity author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublishersEntity publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

}
