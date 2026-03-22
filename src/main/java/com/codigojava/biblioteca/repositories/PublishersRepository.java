package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.PublishersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishersRepository extends JpaRepository<PublishersEntity, Integer> {

    List<PublishersEntity> findByNamePublisherContainingIgnoreCase(String name);
}