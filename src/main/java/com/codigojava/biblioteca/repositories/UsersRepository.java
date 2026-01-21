package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    List<UsersEntity> findByFullnameContainingIgnoreCase(String name);

}
