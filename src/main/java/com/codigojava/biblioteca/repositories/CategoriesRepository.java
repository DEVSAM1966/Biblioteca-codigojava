package com.codigojava.biblioteca.repositories;


import com.codigojava.biblioteca.entities.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {

    List<CategoriesEntity> findByNameCategoryContainingIgnoreCaseOrSubtopicCategoryContainingIgnoreCase(
            String nameCategory,
            String subtopicCategory
    );
}
