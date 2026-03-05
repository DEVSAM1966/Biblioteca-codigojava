package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(
            value = "/public",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<BooksPublicDto>> findAllPublic() {
        return ResponseEntity.ok(this.booksService.findAllPublic());
    }

    @GetMapping(
            value = "/private",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<BooksPublicDto>> findAllPrivate() {
        return ResponseEntity.ok(this.booksService.findAllPrivate());
    }

    @GetMapping(
            value = "/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksDto> findById(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findById(isbn));
    }

    @GetMapping(
            value = "/public/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksPublicIsbnDto> findByIdPublic(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findByIdPublic(isbn));
    }

    @GetMapping(
            value = "/private/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksPublicIsbnDto> findByIdPrivate(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findByIdPublic(isbn));
    }

    @GetMapping(
            value = "/title/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<BooksPublicDto>> findByName(
            @Valid @Size(max = 55) @PathVariable final String name) {
        return ResponseEntity.ok(this.booksService.findByName(name));
    }

    @GetMapping(
            value = "/private/file/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksFileDto> findFileByIdPrivate(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findFileById(isbn));
    }

    @GetMapping(
            value = "/public/file/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksFileDto> findFileByIdPublic(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.findFileById(isbn));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksDto> save(@Validated @RequestBody final BooksRecordDh booksDh) {
        return ResponseEntity.ok(this.booksService.save(booksDh));
    }

    @PutMapping(
            value = "/isbn/{isbn}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksDto> updateById(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn,
            @Validated @RequestBody final BooksRecordDh booksDh) {
        return ResponseEntity.ok(this.booksService.updateById(isbn, booksDh));
    }

    @PutMapping(
            value = "/{isbn}/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BooksDto> updateBookFiles(
            @PathVariable final String isbn,
            @RequestPart("bookCover") final MultipartFile bookCover,
            @RequestPart("bookFile") final MultipartFile bookFile
    ) {
        return ResponseEntity.ok(this.booksService.updateFiles(isbn, bookCover, bookFile));
    }

    @DeleteMapping(
            value = "/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> deleteById(
            @Valid @Size(min = 10, max = 13) @PathVariable final String isbn) {
        return ResponseEntity.ok(this.booksService.deleteById(isbn));
    }

}
