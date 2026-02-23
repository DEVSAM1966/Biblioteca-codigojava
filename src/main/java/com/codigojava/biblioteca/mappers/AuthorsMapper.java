package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.AuthorsRecordDh;
import com.codigojava.biblioteca.dataholders.AuthorsUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.AuthorsDto;
import com.codigojava.biblioteca.entities.AuthorsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {

    AuthorsEntity asEntity(AuthorsRecordDh authorsDh);

    AuthorsEntity asEntity(AuthorsUpdatedRecordDh authorsDh);

    List<AuthorsEntity> asEntityList(List<AuthorsRecordDh>  authorsDhList);

    AuthorsDto asDto(AuthorsEntity authors);

    List<AuthorsDto> asDtoList(List<AuthorsEntity> authorsList);

}
