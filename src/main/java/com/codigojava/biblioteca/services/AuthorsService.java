package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.AuthorsCreatedDh;
import com.codigojava.biblioteca.dataholders.AuthorsUpdatedDh;
import com.codigojava.biblioteca.dataholders.NameValidationGenericDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;

import java.util.List;

public interface AuthorsService {

    List<AuthorsDto> findAll();

    AuthorsDto findById(Integer id);

    List<AuthorsDto> findByName(String name);

    Boolean deleteById(Integer id);

    AuthorsDto save(AuthorsCreatedDh authorsDh);

    AuthorsDto updateById(Integer id, AuthorsUpdatedDh authorsDh);

}
