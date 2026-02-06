package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.services.PublishersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublishersController {

    private final PublishersService publishersService;

    @GetMapping
    public ResponseEntity<List<PublishersDto>> findAll() {
        List<PublishersDto> publishers = publishersService.findAll();
        return ResponseEntity.ok(publishers);
    }
}
