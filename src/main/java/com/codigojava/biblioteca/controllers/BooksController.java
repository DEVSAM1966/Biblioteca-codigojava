package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.mappers.BooksMapper;
import com.codigojava.biblioteca.services.BooksService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    @NonNull
    private BooksService  booksService;

    @NonNull
    private BooksMapper booksMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksDto>> findAll() {
        return ResponseEntity.ok(this.booksService.findAll());
    }

    @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksPublicDto>> findAllPublic() {
        return ResponseEntity.ok(this.booksService.findAllPublic());
    }

    @GetMapping(value = "/private", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BooksPublicDto>> findAllPrivate() {
        return ResponseEntity.ok(this.booksService.findAllPrivate());
    }

    @GetMapping(value = "/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksDto> findById(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findById(isbn));
    }

    @GetMapping(value = "/public/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksPublicIsbnDto> findByIdPublic(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findByIdPublic(isbn));
    }

    @GetMapping(value = "/private/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksPublicIsbnDto> findByIdPrivate(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findByIdPublic(isbn));
    }

}
