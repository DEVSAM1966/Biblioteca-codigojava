package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.AuthorsDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;

import java.util.List;

public interface AuthorsService {

    List<AuthorsDto> findAll();

    AuthorsDto findById(Integer id);

    List<AuthorsDto> findByName(String name);

    Boolean deleteById(Integer id);

    AuthorsDto save(AuthorsDh authorsDh);

}
