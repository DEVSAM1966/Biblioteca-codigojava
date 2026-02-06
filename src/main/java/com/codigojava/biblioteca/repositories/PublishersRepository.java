package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.PublishersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishersRepository extends JpaRepository<PublishersEntity, Integer> {
}
-