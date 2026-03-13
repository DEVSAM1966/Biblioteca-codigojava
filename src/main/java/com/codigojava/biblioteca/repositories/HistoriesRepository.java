package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.HistoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriesRepository extends JpaRepository<HistoriesEntity, Integer> {
}
