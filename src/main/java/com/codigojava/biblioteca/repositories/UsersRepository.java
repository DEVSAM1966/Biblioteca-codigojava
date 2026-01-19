package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
}
