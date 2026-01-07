package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.services.CategoriesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
