package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.AuthorsDto;

import java.util.List;

public interface AuthorsService {

    List<AuthorsDto> findAll();

    AuthorsDto findById(Integer id);

    List<AuthorsDto> findByName(String name);

}
