package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.entities.HistoriesEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.mappers.HistoriesMapper;
import com.codigojava.biblioteca.repositories.HistoriesRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
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
    public HistoriesDto findByLoanId(final Integer loanId) {
        final Optional<HistoriesEntity> historiesOptional = this.historiesRepository.findByLoan_loanId(loanId);

        if (historiesOptional.isPresent()) {
            return this.historiesMapper.asDto(historiesOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not histories in the database with the loanId for loan: " + loanId);
        }
    }

}
