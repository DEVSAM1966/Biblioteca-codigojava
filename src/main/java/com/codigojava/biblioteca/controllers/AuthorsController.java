package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.services.AuthorsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorsDto>> findAll() {
        return ResponseEntity.ok(this.authorsService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorsDto> findById(@Validated @PathVariable Integer id) {
        return ResponseEntity.ok(this.authorsService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorsDto>> findByName(@Validated @PathVariable String name) {
        return ResponseEntity.ok(this.authorsService.findByName(name));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@Validated @PathVariable final Integer id) {
        return ResponseEntity.ok(this.authorsService.deleteById(id));
    }

}
