package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.CategoriesRecordDh;
import com.codigojava.biblioteca.dataholders.CategoriesUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.CategoriesDto;
import com.codigojava.biblioteca.entities.CategoriesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CategoriesMapper {

    CategoriesEntity asEntity(CategoriesRecordDh categoriesDh);

    void updateEntityFromDh(CategoriesUpdatedRecordDh categoriesDh, @MappingTarget CategoriesEntity entity);

    List<CategoriesEntity> asEntityList(List<CategoriesRecordDh> categoriesDhList);

    CategoriesDto asDto(CategoriesEntity categories);

    List<CategoriesDto> asDtoList(List<CategoriesEntity> categoriesList);

}
