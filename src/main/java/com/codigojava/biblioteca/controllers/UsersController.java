package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedDh;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.services.UsersService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.dataholders.NameValidationGenericDh;
import com.codigojava.biblioteca.validators.DhValidator;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @NonNull
    private UsersService usersService;

    @Autowired
    private DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDto>> findAll() {
        return ResponseEntity.ok(this.usersService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDto> findById(@Validated @PathVariable Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.usersService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDto>> findByName(@Validated @PathVariable String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(this.usersService.findByName(name));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.usersService.deleteById(id));
    }

    @DeleteMapping(value = "/drop/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteLogicById(@Validated @PathVariable final Integer id) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.usersService.deleteLogicById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDto> save(@Validated @RequestBody final UsersCreatedDh usersDh) {
        return ResponseEntity.ok(this.usersService.save(usersDh));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDto> updateById(@Validated @PathVariable final Integer id, @Validated @RequestBody final UsersUpdatedDh usersDh) {

        if (id == null || id <= 0) {
            throw new DhValidationException("id", "The id must be a positive integer greater than 0");
        }

        return ResponseEntity.ok(this.usersService.updateById(id, usersDh));
    }

}

