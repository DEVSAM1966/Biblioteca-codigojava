package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.LoansDto;

import java.util.List;

public interface LoansService {

    List<LoansDto> findAll();

    LoansDto findById(Integer id);

    List<LoansDto> findByUserId(Integer id);
}
