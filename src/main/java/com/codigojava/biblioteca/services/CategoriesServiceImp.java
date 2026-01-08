package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.entities.CategoriesEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.mappers.CategoriesMapper;
import com.codigojava.biblioteca.repositories.CategoriesRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public CategoriesDto findById(Integer id) {
        final Optional<CategoriesEntity> categoryOptional = this.categoriesRepository.findById(id);

        if (categoryOptional.isPresent()) {
            return this.categoriesMapper.asDto(categoryOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not categories in the database with the id: " + id);
        }
    }

}
