package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.*;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.services.AuthorsService;
import com.codigojava.biblioteca.validators.DhValidator;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {

    @NonNull
    private AuthorsService authorsService;

    @Autowired
    private DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AuthorsDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> findById(@Validated @PathVariable Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findById(id)));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AuthorsDto>>> findByName(@Validated @PathVariable String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.findByName(name)));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.deleteById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> save(@Validated @RequestBody final AuthorsCreatedDh authorsDh) {
        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.save(authorsDh)));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AuthorsDto>> updateById(@Validated @PathVariable final Integer id, @Validated @RequestBody final AuthorsUpdatedDh authorsDh) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(new ApiResponse<>(this.authorsService.updateById(id, authorsDh)));
    }

}
