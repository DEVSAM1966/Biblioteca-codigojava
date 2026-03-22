package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.LoansCreatedDh;
import com.codigojava.biblioteca.dataholders.LoansUpdatedDh;
import com.codigojava.biblioteca.dtos.LoansDto;

import java.util.List;

public interface LoansService {

    List<LoansDto> findAll();

    LoansDto findById(Integer id);

    List<LoansDto> findByUserId(Integer id);

    List<LoansDto> findByIsbn(String isbn);

    LoansDto save(LoansCreatedDh loansCreateDh);

    Boolean deleteById(Integer id);

    LoansDto updateById(Integer id, LoansUpdatedDh loansUpdateRecordDh);

}
