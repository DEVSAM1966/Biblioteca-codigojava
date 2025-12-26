package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.AuthorsCreatedDh;
import com.codigojava.biblioteca.dataholders.AuthorsUpdatedDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {

    AuthorsEntity asEntity(AuthorsCreatedDh authorsDh);

    AuthorsEntity asEntity(AuthorsUpdatedDh authorsDh);

    List<AuthorsDto> asEntityList(List<AuthorsCreatedDh>  authorsDhList);

    AuthorsDto asDto(AuthorsEntity authors);

    List<AuthorsDto> asDtoList(List<AuthorsEntity> authorsList);

}
