package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;

import java.util.List;

public interface PublishersService {
    List<PublishersDto> findAll();


    PublishersDto createPublisher(PublishersEntity publishers);
}
