package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dataholders.UsersUpdatedDh;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsersMapper {

    UsersEntity asEntity(UsersCreatedDh usersDh);

    void updateEntityFromDh(UsersUpdatedDh usersDh, @MappingTarget UsersEntity entity);

    List<UsersEntity> asEntityList(List<UsersCreatedDh> usersDhList);

    UsersDto asDto(UsersEntity users);

    List<UsersDto> asDtoList(List<UsersEntity> usersList);

}
