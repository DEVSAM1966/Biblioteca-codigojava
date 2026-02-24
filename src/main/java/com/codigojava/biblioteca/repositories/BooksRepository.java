package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<BooksEntity, String> {
}
