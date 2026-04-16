package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.*;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.services.UsersService;
import com.codigojava.biblioteca.validators.annotations.PositiveId;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codigojava.biblioteca.validators.DhValidator;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final DhValidator dhValidator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<UsersDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.usersService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UsersDto>> findById(@PositiveId @PathVariable Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.usersService.findById(id)));
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<UsersDto>>> findByName(@PathVariable final String name) {

        NameValidationGenericDh dh = new NameValidationGenericDh();
        dh.setName(name);

        dhValidator.validate(dh);

        return ResponseEntity.ok(new ApiResponse<>(this.usersService.findByName(name)));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.usersService.deleteById(id)));
    }

    @DeleteMapping(value = "/drop/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteLogicById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.usersService.deleteLogicById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UsersDto>> save(@Valid @RequestBody final UsersCreatedDh usersDh) {

        UsersDto userCreated = this.usersService.save(usersDh);
        URI uri = URI.create("/users/" + userCreated.userId());

        return ResponseEntity.created(uri).body(new ApiResponse<>(userCreated));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UsersDto>> updateById(@PositiveId @PathVariable final Integer id, @Valid @RequestBody final UsersUpdatedDh usersDh) {
        return ResponseEntity.ok(new ApiResponse<>(this.usersService.updateById(id, usersDh)));
    }

    @PutMapping(value = "change-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> changePassword(@Valid @RequestBody final UsersChangePassword userDh) {
        return ResponseEntity.ok(new ApiResponse<>(this.usersService.changePassword(userDh)));
    }

}

