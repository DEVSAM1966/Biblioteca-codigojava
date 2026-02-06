package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.mappers.PublishersMapper;
import com.codigojava.biblioteca.repositories.PublishersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
}
