package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.HistoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriesRepository extends JpaRepository<HistoriesEntity, Integer> {

    List<HistoriesEntity> findByLoan_loanId(Integer loanId);
}
