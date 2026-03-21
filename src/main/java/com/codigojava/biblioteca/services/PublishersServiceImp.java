package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.PublishersMapper;
import com.codigojava.biblioteca.repositories.PublishersRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishersServiceImp implements PublishersService {

    @NonNull
    private final PublishersRepository publishersRepository;

    @NonNull
    private final PublishersMapper publishersMapper;

    @Override
    public List<PublishersDto> findAll() {
        final List<PublishersEntity> publishersList =
                this.publishersRepository.findAll(Sort.by(Sort.Direction.ASC, "publisherId"));

        if (CollectionUtils.isEmpty(publishersList)) {
            log.warn("FindAll for publishers - There are no publishers in the database");
            return Collections.emptyList();
        } else {
            return this.publishersMapper.asDtoList(publishersList);
        }
    }

    @Transactional
    @Override
    public PublishersDto createPublisher(PublishersCreatedRecordDh publisherDh) {

        if (publishersRepository.existsById(publisherDh.publisherId())) {
            log.warn("CreatePublisher - PublisherId {} already exists", publisherDh.publisherId());
            throw new BdNotSaveException("The publisherId " + publisherDh.publisherId() + " already exists.");
        }
        PublishersEntity publisherNew = publishersMapper.asEntity(publisherDh);
        PublishersEntity saved = publishersRepository.save(publisherNew);
        return publishersMapper.asDto(saved);
    }

    @Override
    public PublishersDto findById (Integer id){
        var optionalPublisher=publishersRepository.findById(id);
        if(optionalPublisher.isPresent()){
           return publishersMapper.asDto(optionalPublisher.get());
        }else{
            throw new BdNotFoundException("GET - Publishers: There is not publisher with id {id} in database.");
        }

    }

    public List<PublishersDto> findByName(String name){
         List<PublishersEntity> publisherName=publishersRepository.findByNamePublisherContainingIgnoreCase(name);

         if (CollectionUtils.isEmpty(publisherName)){
             log.warn("FindByName for publishers - There are not publisher in the database");
             throw new BdNotFoundException("GET - Publishers: There is not publisher with namePublisher {name} in database.");
         }else {
             return publishersMapper.asDtoList(publisherName);
         }
    }
}
