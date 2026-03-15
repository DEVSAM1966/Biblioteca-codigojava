package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.HistoriesRecordDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;

import java.util.List;

public interface HistoriesService {

    List<HistoriesDto> findAll();

    HistoriesDto findById(Integer id);

    List<HistoriesDto> findByLoanId(Integer id);

    HistoriesDto save(HistoriesRecordDh historiesDh);

}
