package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.CategoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDto> findAll();

    CategoriesDto findById(Integer id);

    List<CategoriesDto> findByName(String name);

    Boolean deleteById(Integer id);

    CategoriesDto save(CategoriesCreatedDh categoriesCreatedDh);

    CategoriesDto updateById(Integer id, CategoriesUpdatedDh categoriesUpdatedDh);

}
