package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublishersMapper {

    PublishersDto asDto(PublishersEntity publisher);

    List<PublishersDto> asDtoList(List<PublishersEntity> publishersList);

    // Dataholder → Entity
    PublishersEntity asEntity(PublishersCreatedRecordDh publisherDh);
}
