package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersChangePassword;
import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedDh;
import com.codigojava.biblioteca.dtos.UsersDto;

import java.util.List;

public interface UsersService {

    List<UsersDto> findAll();

    UsersDto findById(Integer id);

    List<UsersDto> findByName(String name);

    Boolean deleteById(Integer id);

    UsersDto save(UsersCreatedDh usersCreatedDh);

    UsersDto updateById(Integer id, UsersUpdatedDh usersUpdatedDh);

    Boolean deleteLogicById(Integer id);

    Boolean changePassword(UsersChangePassword usersChangePassword);
}

