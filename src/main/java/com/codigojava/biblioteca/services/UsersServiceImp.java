package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.mappers.UsersMapper;
import com.codigojava.biblioteca.repositories.UsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImp implements UsersService{

    @NonNull
    private final UsersRepository usersRepository;

    @NonNull
    private final UsersMapper usersMapper;

    @Override
    public List<UsersDto> findAll() {
        final List<UsersEntity> usersList =
                usersRepository.findAll(Sort.by(Sort.Direction.ASC, "userId"));

        if (CollectionUtils.isEmpty(usersList)) {
            log.warn("FindAll for users - There are not users in database");
            return Collections.emptyList();
        } else {
            return this.usersMapper.asDtoList(usersList);
        }
    }

}
