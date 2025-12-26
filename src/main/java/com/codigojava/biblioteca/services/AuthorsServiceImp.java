package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.AuthorsCreatedDh;
import com.codigojava.biblioteca.dataholders.AuthorsUpdatedDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.AuthorsMapper;
import com.codigojava.biblioteca.repositories.AuthorsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
                this.authorsRepository.findAll(Sort.by(Sort.Direction.ASC, "authorId"));

        if (CollectionUtils.isEmpty(authorsList)) {
            log.warn("FindAll for authors - There are not authors in the database");
            return Collections.emptyList();
        } else {
            return this.authorsMapper.asDtoList(authorsList);
        }
    }

    @Override
    public AuthorsDto findById( final Integer id) {
        final Optional<AuthorsEntity> authorOptional = this.authorsRepository.findById(id);

        if (authorOptional.isPresent()) {
            return authorsMapper.asDto(authorOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not authors in the database with the id: " + id);
        }
    }

    @Override
    public List<AuthorsDto> findByName(final String name) {
        final List<AuthorsEntity> authorsList =
                this.authorsRepository.findByNameAuthorContainingIgnoreCase(name);

        if (CollectionUtils.isEmpty(authorsList)) {
            log.warn("FindByName for authors - There are not authors in the database");
            return Collections.emptyList();
        } else {
            return this.authorsMapper.asDtoList(authorsList);
        }
    }

    @Override
    public Boolean deleteById(final Integer id) {
        final Optional<AuthorsEntity> existAuthors = this.authorsRepository.findById(id);

        if (existAuthors.isEmpty()) {
            throw new BdNotFoundException("DELETE - No author found with id: " + id);
        }

        try {
            this.authorsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for authors - Error deleting author. Possible cause: {}", e.getMessage());
            throw new BdInternalException( "DELETE - Error deleting author. Possible cause: table missing or DB inconsistency." );
            }

    }

    @Override
    public AuthorsDto save(final AuthorsCreatedDh authorsDh) {

        final AuthorsEntity authors = this.authorsMapper.asEntity(authorsDh);

        try {
            final AuthorsEntity authorsSaved = this.authorsRepository.save(authors);
            return authorsMapper.asDto(authorsSaved);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for authors - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving author. Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for authors - Error saving author. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save author.  Possible cause: BD inconsistency or internal failure.");
        }

    }

    @Override
    public AuthorsDto updateById(final Integer id, final AuthorsUpdatedDh authorsDh) {

        final AuthorsEntity existingAuthor = this.authorsRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("PUT - No author found with id: " + id));

        if (authorsDh.getAuthorId() != null && !authorsDh.getAuthorId().equals(id)) {
            throw new BdNotSaveException(
                    "PUT - Parameters are incorrect: authorId " + authorsDh.getAuthorId() + " is different from id " + id );
        }

        try {
            existingAuthor.setNameAuthor(authorsDh.getNameAuthor());
            final AuthorsEntity updatedAuthor = this.authorsRepository.save(existingAuthor);
            return this.authorsMapper.asDto(updatedAuthor);

        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving author. Possible cause: DB inconsistency or internal failure."
            );
        }

    }


}
