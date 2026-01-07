package com.codigojava.biblioteca.repositories;


import com.codigojava.biblioteca.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
}
