package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedDh;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.UsersMapper;
import com.codigojava.biblioteca.repositories.UsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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

    @Transactional
    @Override
    public Boolean deleteById(final Integer id) {
        final Optional<UsersEntity> existUsers = this.usersRepository.findById(id);

        if (existUsers.isEmpty()) {
            throw  new BdNotFoundException("DELETE- No users found with id: " + id);
        }

        try {
            this.usersRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for users - Error deleting user. Possible cause: {}", e.getMessage());
            throw new BdInternalException( "DELETE - Error deleting user. Possible cause: table missing or DB inconsistency." );
        }
    }

    @Transactional
    @Override
    public Boolean deleteLogicById(final Integer id) {

        UsersEntity user = this.usersRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("DELETE - No users found with id: " + id));

        try {
            user.setUserDrop(true);
            this.usersRepository.save(user);
            return true;

        } catch (Exception e) {
            log.warn("Delete logic for users - Error writing in field userDrop. Possible cause: {}", e.getMessage());
            throw new BdInternalException("DELETE LOGIC - Error deleting user. Possible cause: table missing or DB inconsistency.");
        }
    }

    @Transactional
    @Override
    public UsersDto save(final UsersCreatedDh usersDh) {
        final UsersEntity users = this.usersMapper.asEntity(usersDh);

        try {
            users.setRegistrationDate(LocalDate.now());
            users.setDaysDisciplinary(0);

            final UsersEntity userSaved = this.usersRepository.save(users);
            return usersMapper.asDto(userSaved);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for users - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving user. Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for categories - Error saving user. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save user.  Possible cause: BD inconsistency or internal failure.");
        }
    }

    @Transactional
    @Override
    public UsersDto updateById(final Integer id, final UsersUpdatedDh usersDh) {
        final UsersEntity existingUser = this.usersRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("UPDATE - No user found with id: " + id));

        if (usersDh.userId() != null && !usersDh.userId().equals(id)) {
            throw new BdNotSaveException(
                    "PUT - Parameters are incorrect: userId " + usersDh.userId() + " is different from id " + id );
        }

        try {
            usersMapper.updateEntityFromDh(usersDh, existingUser);

            final UsersEntity updatedUser = this.usersRepository.save(existingUser);

            return this.usersMapper.asDto(updatedUser);
        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving user. Possible cause: DB inconsistency or internal failure."
            );
        }
    }

}
