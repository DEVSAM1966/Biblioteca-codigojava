package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.repositories.PublishersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PublishersService {

    @Autowired
    private PublishersRepository publishersRepository;

    public List<PublishersDto> findAllPublishers() {
        List<PublishersEntity> entities = publishersRepository.findAll();

        if (entities.isEmpty()) {
            log.warn("GET - Publishers: There are not publishers in database.");
            return new ArrayList<>(); 
        }

        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PublishersDto mapToDto(PublishersEntity entity) {
        PublishersDto dto = new PublishersDto();
        dto.setPublisherId(entity.getPublisherId());
        dto.setNamePublisher(entity.getNamePublisher());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setProvince(entity.getProvince());
        dto.setPostalCode(entity.getPostalCode());
        dto.setCountry(entity.getCountry());
        dto.setPhone(entity.getPhone());
        dto.setNotes(entity.getNotes());
       
        return dto;
    }
}