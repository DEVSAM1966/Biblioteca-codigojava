package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dataholders.PublishersUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.PublishersMapper;
import com.codigojava.biblioteca.repositories.PublishersRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        final List<PublishersEntity> publishersList = this.publishersRepository.findAll(Sort.by(Sort.Direction.ASC, "publisherId"));

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
    public PublishersDto findById(Integer id) {
        var optionalPublisher = publishersRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            return publishersMapper.asDto(optionalPublisher.get());
        } else {
            throw new BdNotFoundException("GET - Publishers: There is not publisher with id " + id + " in database.");
        }

    }

    public List<PublishersDto> findByName(String name) {
        List<PublishersEntity> publisherName = publishersRepository.findByNamePublisherContainingIgnoreCase(name);

        if (CollectionUtils.isEmpty(publisherName)) {
            log.warn("FindByName for publishers - There are not publisher in the database");
            throw new BdNotFoundException("GET - Publishers: There is not publisher with namePublisher " + name + " in database.");
        } else {
            return publishersMapper.asDtoList(publisherName);
        }
    }

    @Transactional
    @Override
    public PublishersDto updateById(final Integer id, final PublishersUpdatedRecordDh updatedRecordDh) {

        final PublishersEntity existingPublisher = publishersRepository.findById(id).orElseThrow(() -> new BdNotFoundException("PUT - No publisher found with id: " + id));

        if (updatedRecordDh.publisherId() != null && !updatedRecordDh.publisherId().equals(id)) {
            throw new BdNotSaveException("PUT - Parameters are incorrect: publisherId " + updatedRecordDh.publisherId() + " is different from id " + id);
        }

        try {
            existingPublisher.updateFields(updatedRecordDh);
            final PublishersEntity updated = publishersRepository.save(existingPublisher);
            return publishersMapper.asDto(updated);
        } catch (Exception e) {
            throw new BdInternalException("PUT - Error saving publisher. Possible cause: DB inconsistency or internal failure.");
        }
    }

    @Transactional
    @Override
    public Boolean deleteById(Integer id) {


        Optional<PublishersEntity> existPublisher = publishersRepository.findById(id);
        if (existPublisher.isEmpty()) {
            throw new BdNotFoundException("DELETE - No author found with id: " + id);
        }
        try {
            publishersRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for publishers - Error deleting publisher. Possible cause: {}", e.getMessage());
            throw new BdInternalException("DELETE - Error deleting publisher. Possible cause: table missing or DB inconsistency.");
        }
    }


}

