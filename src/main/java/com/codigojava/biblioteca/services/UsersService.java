package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersRecordDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.UsersDto;

import java.util.List;

public interface UsersService {

    List<UsersDto> findAll();

    UsersDto findById(Integer id);

    List<UsersDto> findByName(String name);

    Boolean deleteById(Integer id);

    UsersDto save(UsersRecordDh usersCreatedDh);

    UsersDto updateById(Integer id, UsersUpdatedRecordDh usersUpdatedDh);

    Boolean deleteLogicById(Integer id);

}

