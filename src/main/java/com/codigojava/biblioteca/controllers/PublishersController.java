package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.services.PublishersService;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublishersController {

    private final PublishersService publishersService;



    @PostMapping
    public ResponseEntity createPublisher (@RequestBody @Valid PublishersCreatedRecordDh create, UriComponentsBuilder uriComponentsBuilder){

        //crear un nuevo publisher

        //metodo para crear en el service
        PublishersDto created = publishersService.createPublisher(create);
        var uri=uriComponentsBuilder.path("/publishers/{id}").buildAndExpand(created.publisherId()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse<>(created));




    }

    @GetMapping
    public ResponseEntity<List<PublishersDto>> findAll() {
        List<PublishersDto> publishers = publishersService.findAll();
        return ResponseEntity.ok(publishers);
    }




}
