package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer> {
}
