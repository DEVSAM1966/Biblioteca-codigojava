package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.HistoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.HistoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;

import java.util.List;

public interface HistoriesService {

    List<HistoriesDto> findAll();

    HistoriesDto findById(Integer id);

    List<HistoriesDto> findByLoanId(Integer id);

    HistoriesDto save(HistoriesCreatedDh historiesDh);

    HistoriesDto updateById(Integer id, HistoriesUpdatedDh historiesDh);

    Boolean deleteById(Integer id);

}
