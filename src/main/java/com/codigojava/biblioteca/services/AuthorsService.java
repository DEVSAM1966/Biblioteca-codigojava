package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.AuthorsDto;

import java.util.List;

public interface AuthorsService {

    List<AuthorsDto> findAll();

}
