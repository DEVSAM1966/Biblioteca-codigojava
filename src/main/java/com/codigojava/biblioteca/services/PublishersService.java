package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface PublishersService {
    List<PublishersDto> findAll();


    PublishersDto createPublisher(PublishersCreatedRecordDh publishers);

    PublishersDto findById(Integer id);

    List<PublishersDto> findByName(@NotBlank String name);
}
