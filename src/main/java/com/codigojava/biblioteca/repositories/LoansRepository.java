package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer> {

    List<LoansEntity> findByUser_UserId(Integer userId);

}
