package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.LoansCreatedDh;
import com.codigojava.biblioteca.dataholders.LoansUpdatedDh;
import com.codigojava.biblioteca.dtos.LoansDto;
import com.codigojava.biblioteca.services.LoansService;
import com.codigojava.biblioteca.validators.annotations.PositiveId;
import com.codigojava.biblioteca.validators.annotations.ValidIsbn;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoansController {

    private final LoansService loansService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<LoansDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(loansService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoansDto>> findById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(loansService.findById(id)));
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<LoansDto>>> findByUserId(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.loansService.findByUserId(id)));
    }

    @GetMapping(value = "/isbn/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<LoansDto>>> findByIsbn(@ValidIsbn @PathVariable final String isbn) {

        return ResponseEntity.ok(new ApiResponse<>(this.loansService.findByIsbn(isbn)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoansDto>> save(@Valid @RequestBody final LoansCreatedDh loansDh) {

        LoansDto loanCreated = this.loansService.save(loansDh);
        URI uri = URI.create("/loans/" + loanCreated.loanId());

        return ResponseEntity.created(uri).body(new ApiResponse<>(loanCreated));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.loansService.deleteById(id)));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<LoansDto>> updateById(@PositiveId @PathVariable final Integer id, @Valid @RequestBody final LoansUpdatedDh loansDh) {

        return ResponseEntity.ok(new ApiResponse<>(this.loansService.updateById(id, loansDh)));
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<LoansDto>>> findMyLoans(Authentication authentication) {

        // authentication.getPrincipal() es UsersEntity
        var user = (com.codigojava.biblioteca.entities.UsersEntity) authentication.getPrincipal();
        Integer userId = user.getUserId();

        return ResponseEntity.ok(new ApiResponse<>(this.loansService.findByUserId(userId)));
    }


}
