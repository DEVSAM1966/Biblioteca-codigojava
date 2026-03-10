package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.LoansDto;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.services.LoansService;
import com.codigojava.biblioteca.validators.DhValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoansController {

    @NonNull
    private LoansService loansService;

    @Autowired
    private DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoansDto>> findAll() {
        return ResponseEntity.ok(loansService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansDto> findById(@Validated @PathVariable final Integer id) {


        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(loansService.findById(id));
    }

    @GetMapping(value = "/userid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoansDto>> findByUserId(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.loansService.findByUserId(id));
    }

    @GetMapping(value = "/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoansDto>> findByIsbn(@Validated @PathVariable final String isbn) {

        return ResponseEntity.ok(this.loansService.findByIsbn(isbn));
    }

}
