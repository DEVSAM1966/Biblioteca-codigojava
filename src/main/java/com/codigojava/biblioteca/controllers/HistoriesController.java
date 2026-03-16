package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.HistoriesRecordDh;
import com.codigojava.biblioteca.dataholders.HistoriesUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.services.HistoriesService;
import com.codigojava.biblioteca.validators.DhValidator;
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
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoriesController {

    @NonNull
    private final HistoriesService historiesService;

    @NonNull
    private final DhValidator  dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriesDto>> findAll() {
        return ResponseEntity.ok(this.historiesService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoriesDto> findById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.historiesService.findById(id));
    }

    @GetMapping(value = "/loans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoriesDto>> findByLoanId(@PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.historiesService.findByLoanId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoriesDto> save(@Validated @RequestBody final HistoriesRecordDh historiesDh) {
        return ResponseEntity.ok(this.historiesService.save(historiesDh));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoriesDto> updateById(@Validated @PathVariable final Integer id, @Validated @RequestBody final HistoriesUpdatedRecordDh historiesDh) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.historiesService.updateById(id, historiesDh));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.historiesService.deleteById(id));
    }

}
