package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.BooksEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<BooksEntity, String> {

    List<BooksEntity> findByTitleContainingIgnoreCase(String title);

    @Query("""
        SELECT b FROM BooksEntity b
        WHERE (:authorId IS NULL OR b.author.authorId = :authorId)
          AND (:categoryId IS NULL OR b.category.categoryId = :categoryId)
    """)
    Page<BooksEntity> findAllWithFilters(
            @Param("authorId") Long authorId,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );
}
