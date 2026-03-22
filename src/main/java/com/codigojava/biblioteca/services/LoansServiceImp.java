package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.LoansCreatedDh;
import com.codigojava.biblioteca.dataholders.LoansUpdatedDh;
import com.codigojava.biblioteca.dtos.LoansDto;
import com.codigojava.biblioteca.entities.LoansEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.LoansMapper;
import com.codigojava.biblioteca.repositories.LoansRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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

    @Override
    public List<LoansDto> findByUserId(final Integer id) {
        final List<LoansEntity> loansList = this.loansRepository.findByUser_UserId(id);

        if (CollectionUtils.isEmpty(loansList)) {
            log.warn("FindByUserId for Loans - There are not loans in database con userId: {}", id);
            return Collections.emptyList();
        } else {
            return this.loansMapper.asDtoList(loansList);
        }
    }

    @Override
    public List<LoansDto> findByIsbn(final String isbn) {
        final  List<LoansEntity> loansList = this.loansRepository.findByIsbn(isbn);

        if (CollectionUtils.isEmpty(loansList)) {
            log.warn("FindByIsbn for loans - There are not loans in database con isbn: {}", isbn);
            return Collections.emptyList();
        } else {
            return this.loansMapper.asDtoList(loansList);
        }
    }

    @Transactional
    @Override
    public LoansDto save(final LoansCreatedDh loansCreateDh) {
        final LoansEntity loans = this.loansMapper.asEntity(loansCreateDh);

        // Si loanDate viene null → asignar fecha actual
        if (loans.getLoanDate() == null) {
            loans.setLoanDate(LocalDate.now());
        }

        // Si returnDate viene null → asignar loanDate + 7 días
        if (loans.getReturnDate() == null) {
            loans.setReturnDate(loans.getLoanDate().plusDays(7));
        }

        try {
            final LoansEntity loanSaved = this.loansRepository.save(loans);
            return loansMapper.asDto(loanSaved);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for loans - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving loan. Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for loans - Error saving loan. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save loan.  Possible cause: BD inconsistency or internal failure.");
        }
    }

    @Transactional
    @Override
    public Boolean deleteById(final Integer id) {
        final Optional<LoansEntity> loansOptional = this.loansRepository.findById(id);

        if (loansOptional.isEmpty()) {
            throw new BdNotFoundException("DELETE - No loans found with id: " + id);
        }

        try {
            this.loansRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for loans - Error deleting loan. Possible cause: {}", e.getMessage());
            throw new BdInternalException( "DELETE - Error deleting loan. Possible cause: table missing or DB inconsistency.");
        }

    }

    @Transactional
    @Override
    public LoansDto updateById(final Integer id, final LoansUpdatedDh loansDh) {
        final LoansEntity existingLoan = this.loansRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("PUT - No loan found with id: " + id));

        // Extraer valores de loansDh
        LocalDate newLoanDate = loansDh.loanDate();
        LocalDate newReturnDate = loansDh.returnDate();

        // Actualizar si vienen valores
        if (newLoanDate != null) {
            existingLoan.setLoanDate(newLoanDate);
        }

        if (newReturnDate != null) {
            existingLoan.setReturnDate(newReturnDate);
        }

        // Guardamos cambios
        try {
            final LoansEntity updatedLoan = this.loansRepository.save(existingLoan);

            return this.loansMapper.asDto(updatedLoan);
        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving loan. Possible cause: DB inconsistency or internal failure."
            );
        }
    }

}
