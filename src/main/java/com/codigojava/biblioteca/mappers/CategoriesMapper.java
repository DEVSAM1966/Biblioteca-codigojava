package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.CategoriesCreatedDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.entities.CategoriesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {

    CategoriesEntity asEntity(CategoriesCreatedDh categoriesDh);

    CategoriesEntity asEntity(CategoriesUpdatedDh categoriesDh);

    List<CategoriesEntity> asEntityList(List<CategoriesCreatedDh> categoriesDhList);

    CategoriesDto asDto(CategoriesEntity categories);

    List<CategoriesDto> asDtoList(List<CategoriesEntity> categoriesList);

}
