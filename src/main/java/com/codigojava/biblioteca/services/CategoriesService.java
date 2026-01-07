package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.CategoriesDto;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDto> findAll();

}
