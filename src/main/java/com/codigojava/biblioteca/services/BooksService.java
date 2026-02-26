package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;

import java.util.List;

public interface BooksService {

    // Obtener una lista total de libros

    List<BooksDto> findAll();

    List<BooksPublicDto> findAllPublic();

    List<BooksPublicDto> findAllPrivate();


    // Obtener un libro por su isbn

    BooksDto findById(String isbn);

    BooksPublicIsbnDto findByIdPublic(String isbn);

    BooksPublicIsbnDto findByIdPrivate(String isbn);

}
