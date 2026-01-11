package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.CategoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.services.CategoriesService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    @NonNull
    private CategoriesService categoriesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDto>> listAll() {
        return ResponseEntity.ok(this.categoriesService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> findById(@Validated @PathVariable Integer id) {
        return ResponseEntity.ok(this.categoriesService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesDto>> findByName(@Validated @PathVariable String name) {
        return ResponseEntity.ok(this.categoriesService.findByName(name));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@Validated @PathVariable final Integer id) {
        return ResponseEntity.ok(this.categoriesService.deleteById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> save(@Validated @RequestBody final CategoriesCreatedDh categoriesDh) {
        return ResponseEntity.ok(this.categoriesService.save(categoriesDh));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDto> updateById(@Validated @PathVariable final Integer id, @Validated @RequestBody final CategoriesUpdatedDh categoriesDh) {
        return ResponseEntity.ok(this.categoriesService.updateById(id, categoriesDh));
    }

}
