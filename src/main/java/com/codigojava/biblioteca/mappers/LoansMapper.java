package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.LoansRecordDh;
import com.codigojava.biblioteca.dtos.LoansDto;
import com.codigojava.biblioteca.entities.LoansEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LoansMapper {

    LoansEntity asEntity(LoansRecordDh loansDh);

    List<LoansEntity> asEntityList(List<LoansRecordDh> loansDh);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "isbn", source = "book.isbn")
    LoansDto asDto(LoansEntity loansEntity);

    List<LoansDto> asDtoList(List<LoansEntity> loansEntity);

}
