package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.HistoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.HistoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.exceptions.DhValidationException;
import com.codigojava.biblioteca.services.HistoriesService;
import com.codigojava.biblioteca.validators.DhValidator;
import com.codigojava.biblioteca.validators.annotations.PositiveId;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoriesController {

    private final HistoriesService historiesService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<HistoriesDto>>> findAll() {

        return ResponseEntity.ok(new ApiResponse<>(this.historiesService.findAll()));
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<HistoriesDto>> findById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.historiesService.findById(id)));
    }

    @GetMapping(value = "/loans/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<HistoriesDto>>> findByLoanId(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.historiesService.findByLoanId(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<HistoriesDto>> save(@Valid @RequestBody final HistoriesCreatedDh historiesDh) {

        HistoriesDto historyCreated = this.historiesService.save(historiesDh);
        URI uri = URI.create("/histories/" + historyCreated.historyId());

        return ResponseEntity.created(uri).body(new ApiResponse<>(historyCreated));
    }

    @PutMapping(value = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<HistoriesDto>> updateById(@PositiveId @PathVariable final Integer id, @Valid @RequestBody final HistoriesUpdatedDh historiesDh) {

        return ResponseEntity.ok(new ApiResponse<>(this.historiesService.updateById(id, historiesDh)));
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@PositiveId @PathVariable final Integer id) {

        return ResponseEntity.ok(new ApiResponse<>(this.historiesService.deleteById(id)));
    }

}
