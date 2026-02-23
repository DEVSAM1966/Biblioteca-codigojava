package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.CategoriesRecordDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;

import java.util.List;

public interface CategoriesService {

    List<CategoriesDto> findAll();

    CategoriesDto findById(Integer id);

    List<CategoriesDto> findByName(String name);

    Boolean deleteById(Integer id);

    CategoriesDto save(CategoriesRecordDh categoriesCreatedDh);

    CategoriesDto updateById(Integer id, CategoriesUpdatedRecordDh categoriesUpdatedDh);

}
