package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.services.UsersService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @NonNull
    private UsersService usersService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDto>> findAll() {
        return ResponseEntity.ok(this.usersService.findAll());
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDto> findById(@Validated @PathVariable Integer id) {
        return ResponseEntity.ok(this.usersService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersDto>> findByName(@Validated @PathVariable String name) {
        return ResponseEntity.ok(this.usersService.findByName(name));
    }

}
