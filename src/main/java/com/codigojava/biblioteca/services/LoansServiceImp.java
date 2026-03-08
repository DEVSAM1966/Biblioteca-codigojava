package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.LoansDto;
import com.codigojava.biblioteca.entities.LoansEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.mappers.LoansMapper;
import com.codigojava.biblioteca.repositories.LoansRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoansServiceImp implements LoansService {

    @NonNull
    private final LoansRepository loansRepository;

    @NonNull
    private final LoansMapper loansMapper;

    @Override
    public List<LoansDto> findAll() {
        final List<LoansEntity> loansList =
                this.loansRepository.findAll(Sort.by(Sort.Direction.ASC, "loanId"));

        if (CollectionUtils.isEmpty(loansList)) {
            log.warn("FindAll for Loans - There are not loans in database");
            return Collections.emptyList();
        } else {
            return this.loansMapper.asDtoList(loansList);
        }
    }

    @Override
    public LoansDto findById(final Integer id) {
        final Optional<LoansEntity> loansOptional = this.loansRepository.findById(id);

        if (loansOptional.isPresent()) {
            return this.loansMapper.asDto(loansOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not loans in the database with the id: " + id);
        }
    }

}
