package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {

    AuthorsDto asDto(AuthorsEntity authors);

    List<AuthorsDto> asDtoList(List<AuthorsEntity> authorsList);

}
