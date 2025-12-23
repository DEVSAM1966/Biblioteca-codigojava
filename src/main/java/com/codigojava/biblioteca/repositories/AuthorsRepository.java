package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.AuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorsRepository extends JpaRepository<AuthorsEntity, Integer> {

    List<AuthorsEntity> findByNameAuthorContainingIgnoreCase(String name);

}
