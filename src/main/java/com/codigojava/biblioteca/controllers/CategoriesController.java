package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.*;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.services.CategoriesService;
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
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.validators.DhValidator;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    private final DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CategoriesDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.categoriesService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoriesDto>> findById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.categoriesService.findById(id)));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CategoriesDto>>> findByName(@PathVariable final String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(new ApiResponse<>(this.categoriesService.findByName(name)));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.categoriesService.deleteById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoriesDto>> save(@Valid @RequestBody final CategoriesCreatedDh categoriesDh) {

        CategoriesDto categoryCreated = this.categoriesService.save(categoriesDh);
        URI uri = URI.create("/categories/" + categoryCreated.categoryId());

        return ResponseEntity.created(uri).body(new ApiResponse<>(categoryCreated));

    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoriesDto>> updateById(@PositiveId @PathVariable final Integer id, @Valid @RequestBody final CategoriesUpdatedDh categoriesDh) {

        return ResponseEntity.ok(new ApiResponse<>(this.categoriesService.updateById(id, categoriesDh)));
    }

}
