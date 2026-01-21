package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.mappers.UsersMapper;
import com.codigojava.biblioteca.repositories.UsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public UsersDto findById(final Integer id) {
        final Optional<UsersEntity> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            return this.usersMapper.asDto(userOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not users in the database with the id: " + id);
        }
    }

    @Override
    public List<UsersDto> findByName(final String name) {
        final List<UsersEntity> usersList =
                this.usersRepository.findByFullnameContainingIgnoreCase(name);

        if (CollectionUtils.isEmpty(usersList)) {
            log.warn("FindAll for users - There are not users in database with name: {}", name);
            return Collections.emptyList();
        } else {
            return this.usersMapper.asDtoList(usersList);
        }
    }

}
