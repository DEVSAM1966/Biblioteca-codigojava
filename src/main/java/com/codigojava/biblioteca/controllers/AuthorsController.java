package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.*;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.services.AuthorsService;
import com.codigojava.biblioteca.validators.DhValidator;
import com.codigojava.biblioteca.validators.annotations.PositiveId;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorsService authorsService;

    private final DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AuthorsDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> findById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findById(id)));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AuthorsDto>>> findByName(@PathVariable final String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findByName(name)));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.deleteById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> save(@Valid @RequestBody final AuthorsCreatedDh authorsDh) {

        AuthorsDto authorCreated = this.authorsService.save(authorsDh);
        URI uri = URI.create("/authors/" + authorCreated.authorId());

        return ResponseEntity.created(uri).body(new ApiResponse<>(authorCreated));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> updateById(@PositiveId @PathVariable final Integer id, @Valid @RequestBody final AuthorsUpdatedDh authorsDh) {

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.updateById(id, authorsDh)));
    }

}
