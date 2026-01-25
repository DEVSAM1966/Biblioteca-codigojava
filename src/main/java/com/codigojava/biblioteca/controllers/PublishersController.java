package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.services.PublishersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublishersController {

    @Autowired
    private PublishersService publishersService;

    @GetMapping
    public ResponseEntity<List<PublishersDto>> getAllPublishers() {
        List<PublishersDto> publishers = publishersService.findAllPublishers();
        return ResponseEntity.ok(publishers);
    }
}
