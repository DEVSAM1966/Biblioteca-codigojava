package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.BooksDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.services.BooksService;
import com.codigojava.biblioteca.validators.annotations.ValidIsbn;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BooksService  booksService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BooksDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findAll()));

    }

    @GetMapping(
            value = "/public",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<BooksPublicDto>>> findAllPublic(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findAllPublic(page, limit, authorId, categoryId)));
    }

    @GetMapping(
            value = "/private",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<BooksPublicDto>>> findAllPrivate(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long categoryId
    ) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findAllPrivate(page, limit, authorId, categoryId)));
    }

    @GetMapping(
            value = "/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksDto>> findById(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findById(isbn)));
    }

    @GetMapping(
            value = "/public/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksPublicIsbnDto>> findByIdPublic(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findByIdPublic(isbn)));
    }

    @GetMapping(
            value = "/private/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksPublicIsbnDto>> findByIdPrivate(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findByIdPublic(isbn)));
    }

    @GetMapping(
            value = "/title/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<List<BooksPublicDto>>> findByName(
            @Valid @Size(max = 55) @PathVariable final String name) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findByName(name)));
    }

    @GetMapping(
            value = "/private/file/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksFileDto>> findFileByIdPrivate(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findFileById(isbn)));
    }

    @GetMapping(
            value = "/public/file/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksFileDto>> findFileByIdPublic(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.findFileById(isbn)));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksDto>> save(@Valid @RequestBody final BooksDh booksDh) {

        BooksDto bookCreated = this.booksService.save(booksDh);
        URI uri = URI.create("/books/" + bookCreated.isbn());

        return ResponseEntity.created(uri).body(new ApiResponse<>(bookCreated));
    }

    @PutMapping(
            value = "/isbn/{isbn}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksDto>> updateById(
            @ValidIsbn @PathVariable final String isbn,
            @Valid @RequestBody final BooksDh booksDh) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.updateById(isbn, booksDh)));
    }

    @PutMapping(
            value = "/{isbn}/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<BooksDto>> updateBookFiles(
            @ValidIsbn @PathVariable final String isbn,
            @RequestPart("bookCover") final MultipartFile bookCover,
            @RequestPart("bookFile") final MultipartFile bookFile
    ) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.updateFiles(isbn, bookCover, bookFile)));
    }

    @DeleteMapping(
            value = "/isbn/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<Boolean>> deleteById(
            @ValidIsbn @PathVariable final String isbn) {
        return ResponseEntity.ok(new ApiResponse<>(this.booksService.deleteById(isbn)));
    }

}
