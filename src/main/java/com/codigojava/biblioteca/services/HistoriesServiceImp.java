package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.HistoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.HistoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.entities.HistoriesEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.HistoriesMapper;
import com.codigojava.biblioteca.repositories.HistoriesRepository;

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
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class HistoriesServiceImp implements HistoriesService {

    @NonNull
    private final HistoriesRepository historiesRepository;

    @NonNull
    private final HistoriesMapper historiesMapper;

    @Override
    public List<HistoriesDto> findAll() {
        final List<HistoriesEntity> historyList =
                this.historiesRepository.findAll(Sort.by(Sort.Direction.ASC, "historyId"));

        if (CollectionUtils.isEmpty(historyList)) {
            log.warn("FindAll for Histories - There are not histories in database");
            return Collections.emptyList();
        } else {
            return this.historiesMapper.asDtoList(historyList);
        }
    }

    @Override
    public HistoriesDto findById(final Integer id) {
        final Optional<HistoriesEntity> historiesOptional = this.historiesRepository.findById(id);

        if (historiesOptional.isPresent()) {
            return this.historiesMapper.asDto(historiesOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not histories in the database with the id: " + id);
        }
    }

    @Override
    public List<HistoriesDto> findByLoanId(final Integer loanId) {
        final List<HistoriesEntity> histories = this.historiesRepository.findByLoan_loanId(loanId);

        if (CollectionUtils.isEmpty(histories)) {
            log.warn("FindByLoanId for histories - There are not histories in database with loanId: {}", loanId);
            return Collections.emptyList();

        } else {
            return this.historiesMapper.asDtoList(histories);
        }
    }

    @Transactional
    @Override
    public HistoriesDto save(final HistoriesCreatedDh historiesDh) {
        final HistoriesEntity histories = this.historiesMapper.asEntity(historiesDh);

        try {
            final HistoriesEntity historySaved = this.historiesRepository.save(histories);
            return this.historiesMapper.asDto(historySaved);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for histories - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving history. Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for histories - Error saving history. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save history.  Possible cause: BD inconsistency or internal failure.");
        }
    }

    @Transactional
    @Override
    public HistoriesDto updateById(final Integer id, final HistoriesUpdatedDh historiesDh) {
        final HistoriesEntity existingHistory = this.historiesRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("PUT - No history found with id: " + id));

        if (historiesDh.historyId() == null || historiesDh.historyId() != id) {
            throw new BdNotSaveException(
                    "PUT - Parameters are incorrect:" +
                            " historyId " + historiesDh.historyId() + " is different from id " + id );
        }

        try {
            historiesMapper.updateEntityFromDh(historiesDh, existingHistory);

            final HistoriesEntity updatedHistory = this.historiesRepository.save(existingHistory);

            return this.historiesMapper.asDto(updatedHistory);

        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving history. Possible cause: DB inconsistency or internal failure."
            );
        }
    }

    @Transactional
    @Override
    public Boolean deleteById(final Integer id) {
        final Optional<HistoriesEntity> existingHistories = this.historiesRepository.findById(id);

        if (existingHistories.isEmpty()) {
            throw new BdNotFoundException("DELETE - No histories found with id: " + id);
        }

        try {
            this.historiesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for histories - Error deleting history. Possible cause: {}", e.getMessage());
            throw new BdInternalException( "DELETE - Error deleting history. Possible cause: table missing or DB inconsistency." );
        }
    }

}
