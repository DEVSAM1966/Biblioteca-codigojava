package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.HistoriesDto;

import java.util.List;

public interface HistoriesService {

    List<HistoriesDto> findAll();

    HistoriesDto findById(Integer id);

    HistoriesDto findByLoanId(Integer id);

}
