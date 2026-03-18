package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.CategoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.entities.CategoriesEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.CategoriesMapper;
import com.codigojava.biblioteca.repositories.CategoriesRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriesServiceImp implements CategoriesService {

    @NonNull
    private final CategoriesRepository categoriesRepository;

    @NonNull
    private final CategoriesMapper categoriesMapper;

    @Override
    public List<CategoriesDto> findAll() {
        final List<CategoriesEntity> categoriesList =
                this.categoriesRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryId"));

        if (CollectionUtils.isEmpty(categoriesList)) {
            log.warn("FindAll for categories - There are not categories in database");
            return Collections.emptyList();
        } else {
            return this.categoriesMapper.asDtoList(categoriesList);
        }
    }

    @Override
    public CategoriesDto findById(final Integer id) {
        final Optional<CategoriesEntity> categoryOptional = this.categoriesRepository.findById(id);

        if (categoryOptional.isPresent()) {
            return this.categoriesMapper.asDto(categoryOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not categories in the database with the id: " + id);
        }
    }

    @Override
    public List<CategoriesDto> findByName(final String name) {
        final List<CategoriesEntity> categoriesList =
                this.categoriesRepository
                        .findByNameCategoryContainingIgnoreCaseOrSubtopicCategoryContainingIgnoreCase(name, name);

        if (CollectionUtils.isEmpty(categoriesList)) {
            log.warn("FindByName for categories - There are not categories in the database with name: {}", name);
            return Collections.emptyList();
        } else {
            return this.categoriesMapper.asDtoList(categoriesList);
        }
    }

    @Override
    public Boolean deleteById(final Integer id) {
        final Optional<CategoriesEntity> existCategories = this.categoriesRepository.findById(id);

        if (existCategories.isEmpty()) {
            throw new BdNotFoundException("DELETE - No categories found with id: " + id);
        }

        try {
            this.categoriesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete for categories - Error deleting category. Possible cause: {}", e.getMessage());
            throw new BdInternalException( "DELETE - Error deleting category. Possible cause: table missing or DB inconsistency." );
        }
    }

    @Override
    public CategoriesDto save(final CategoriesCreatedDh categoriesDh) {
        final CategoriesEntity categories = this.categoriesMapper.asEntity(categoriesDh);

        try {
            final CategoriesEntity categorySaved = this.categoriesRepository.save(categories);
            return categoriesMapper.asDto(categorySaved);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for categories - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving category. Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for categories - Error saving category. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save category.  Possible cause: BD inconsistency or internal failure.");
        }
    }

    @Override
    public CategoriesDto updateById(final Integer id, final CategoriesUpdatedDh categoriesDh) {
        final CategoriesEntity existingCategory = this.categoriesRepository.findById(id)
                .orElseThrow(() -> new BdNotFoundException("PUT - No category found with id: " + id));

        if (categoriesDh.categoryId() != null && !categoriesDh.categoryId().equals(id)) {
            throw new BdNotSaveException(
                    "PUT - Parameters are incorrect: categoryId " + categoriesDh.categoryId() + " is different from id " + id );
        }

        try {
            categoriesMapper.updateEntityFromDh(categoriesDh, existingCategory);

            final CategoriesEntity updatedCategory = this.categoriesRepository.save(existingCategory);

            return this.categoriesMapper.asDto(updatedCategory);

        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving category. Possible cause: DB inconsistency or internal failure."
            );
        }
    }
}
