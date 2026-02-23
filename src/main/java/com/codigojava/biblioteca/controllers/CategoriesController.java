package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.*;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.services.CategoriesService;
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
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.validators.DhValidator;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    @NonNull
    private CategoriesService categoriesService;

    @Autowired
    private DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDto>> findAll() {
        return ResponseEntity.ok(this.categoriesService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> findById(@Validated @PathVariable Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.categoriesService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDto>> findByName(@Validated @PathVariable String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(this.categoriesService.findByName(name));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.categoriesService.deleteById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> save(@Validated @RequestBody final CategoriesRecordDh categoriesDh) {
        return ResponseEntity.ok(this.categoriesService.save(categoriesDh));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> updateById(@Valid @PathVariable @Min(1) final Integer id, @Validated @RequestBody final CategoriesUpdatedRecordDh categoriesDh) {
        return ResponseEntity.ok(this.categoriesService.updateById(id, categoriesDh));
    }

}
