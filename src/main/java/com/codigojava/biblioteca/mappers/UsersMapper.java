package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.UsersRecordDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsersMapper {

    @Mapping(target = "role", defaultValue = "USER")
    UsersEntity asEntity(UsersRecordDh usersDh);

    void updateEntityFromDh(UsersUpdatedRecordDh usersDh, @MappingTarget UsersEntity entity);

    List<UsersEntity> asEntityList(List<UsersRecordDh> usersDhList);

    UsersDto asDto(UsersEntity users);

    List<UsersDto> asDtoList(List<UsersEntity> usersList);

}
