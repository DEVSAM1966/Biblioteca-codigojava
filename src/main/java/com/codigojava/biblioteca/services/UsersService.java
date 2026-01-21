package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.UsersDto;

import java.util.List;

public interface UsersService {

    List<UsersDto> findAll();

    UsersDto findById(Integer id);

    List<UsersDto> findByName(String name);

}
