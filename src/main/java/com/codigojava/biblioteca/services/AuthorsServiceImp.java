package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import com.codigojava.biblioteca.mappers.AuthorsMapper;
import com.codigojava.biblioteca.repositories.AuthorsRepository;
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
public class AuthorsServiceImp implements AuthorsService {

    @NonNull
    private final AuthorsRepository authorsRepository;

    @NonNull
    private final AuthorsMapper authorsMapper;

    @Override
    public List<AuthorsDto> findAll() {
        final List<AuthorsEntity> authorsList =
                (List<AuthorsEntity>) this.authorsRepository.findAll(Sort.by(Sort.Direction.ASC, "authorId"));

        if (CollectionUtils.isEmpty(authorsList)) {
            log.warn("findAll for authors - There are not authors in the database");
            return Collections.emptyList();
        } else {
            return this.authorsMapper.asDtoList(authorsList);
        }
    }
}
