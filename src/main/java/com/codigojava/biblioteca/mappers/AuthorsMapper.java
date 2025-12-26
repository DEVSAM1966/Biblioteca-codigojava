package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.AuthorsDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {

    AuthorsEntity asEntity(AuthorsDh authorsDh);

    List<AuthorsDto> asEntityList(List<AuthorsDh>  authorsDhList);

    AuthorsDto asDto(AuthorsEntity authors);

    List<AuthorsDto> asDtoList(List<AuthorsEntity> authorsList);

}
