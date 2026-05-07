package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.BooksDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BooksService {

    // Método GET - Obtener una lista total de libros (distintas maneras)
    List<BooksDto> findAll();

    List<BooksPublicDto> findAllPublic(int page, int limit, Long authorId, Long categoryId);

    List<BooksPublicDto> findAllPrivate(int page, int limit, Long authorId, Long categoryId);

    // Método GET - Obtener un libro por su isbn (distintas maneras)
    BooksDto findById(String isbn);

    BooksPublicIsbnDto findByIdPublic(String isbn);

    BooksPublicIsbnDto findByIdPrivate(String isbn);

    // Método GET - Obtener un libro o varios según patron contenido en name, se busca por title
    List<BooksPublicDto> findByName(String name);

    // Método GET - Obtener la ruta + nombre del fichero PDF
    BooksFileDto findFileById(String isbn);

    // Método POST - Crear un libro
    BooksDto save(BooksDh bookDh);

    // Método PUT - Modificar un libro y NO sube ficheros (portada y PDF)
    BooksDto updateById(String ibsn, BooksDh bookDh);

    // Método PUT - Añadir ficheros portada y libro
    BooksDto updateFiles(String isbn, MultipartFile bookCover, MultipartFile bookFile);

    // Método DELETE - Borrar un libro a nivel de BD y borrado físico de ficheros.
    Boolean deleteById(String isbn);

}
