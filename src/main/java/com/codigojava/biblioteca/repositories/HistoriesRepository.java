package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.HistoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoriesRepository extends JpaRepository<HistoriesEntity, Integer> {


    Optional<HistoriesEntity> findByLoan_loanId(Integer loanId);
}
