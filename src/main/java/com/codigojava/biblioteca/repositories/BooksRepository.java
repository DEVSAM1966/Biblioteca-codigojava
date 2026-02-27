package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<BooksEntity, String> {

    List<BooksEntity> findByTitleContainingIgnoreCase(String title);

}
