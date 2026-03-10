package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoansRepository extends JpaRepository<LoansEntity, Integer> {

    List<LoansEntity> findByUser_UserId(Integer userId);

    @Query("""
            SELECT l FROM LoansEntity l 
            WHERE l.book.isbn = :isbn
            """)
    List<LoansEntity> findByIsbn(@Param("isbn") String isbn);

}
