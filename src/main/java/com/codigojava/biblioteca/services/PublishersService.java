package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import jakarta.validation.Valid;

import java.util.List;

public interface PublishersService {
    List<PublishersDto> findAll();


    PublishersDto createPublisher(PublishersCreatedRecordDh publishers);
}
