package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;

import java.util.List;

public interface BooksService {

    // Método GET - Obtener una lista total de libros (distintas maneras)
    List<BooksDto> findAll();

    List<BooksPublicDto> findAllPublic();

    List<BooksPublicDto> findAllPrivate();

    // Método GET - Obtener un libro por su isbn (distintas maneras)
    BooksDto findById(String isbn);

    BooksPublicIsbnDto findByIdPublic(String isbn);

    BooksPublicIsbnDto findByIdPrivate(String isbn);

    // Método GET - Obtener un libro o varios según patron contenido en name, se busca por title
    List<BooksPublicDto> findByName(String name);

    // Método GET - Obtener la ruta + nombre del fichero PDF
    BooksFileDto findFileById(String isbn);

    // Método POST - Crear un libro
    BooksDto save(BooksRecordDh bookDh);

}
