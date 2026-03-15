package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.HistoriesRecordDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.entities.HistoriesEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.HistoriesMapper;
import com.codigojava.biblioteca.repositories.HistoriesRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    @Override
    public HistoriesDto save(final HistoriesRecordDh historiesDh) {
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

}
