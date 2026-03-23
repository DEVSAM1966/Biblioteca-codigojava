package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dataholders.PublishersUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.services.PublishersService;
import com.codigojava.biblioteca.validators.annotations.PositiveId;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Validated
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

    @GetMapping("/id/{id}")
    public ResponseEntity findById (@Validated @PathVariable @PositiveId Integer id){

        var findById= publishersService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(findById));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<PublishersDto>>> findByName(@PathVariable @NotBlank String name){
        List<PublishersDto> findByName=publishersService.findByName(name);
        return ResponseEntity.ok(new ApiResponse<>(findByName));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ApiResponse<PublishersDto>> updateById(@Validated @PathVariable @PositiveId Integer id, @RequestBody @Valid PublishersUpdatedRecordDh updatedRecordDh){
        var updatePublisher = publishersService.updateById(id,updatedRecordDh);
        return ResponseEntity.ok(new ApiResponse<>(updatePublisher));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable Integer id) {
        var delete= publishersService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(delete));
    }







}
