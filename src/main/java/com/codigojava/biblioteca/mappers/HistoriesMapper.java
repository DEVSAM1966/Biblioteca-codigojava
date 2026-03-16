package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.HistoriesRecordDh;
import com.codigojava.biblioteca.dataholders.HistoriesUpdatedRecordDh;
import com.codigojava.biblioteca.dtos.HistoriesDto;
import com.codigojava.biblioteca.entities.HistoriesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface HistoriesMapper {

    @Mapping(target = "loan.loanId", source = "loanId")
    HistoriesEntity asEntity(HistoriesRecordDh historyDh);

    List<HistoriesEntity> asEntityList(List<HistoriesRecordDh> historiesDh);

    void updateEntityFromDh(HistoriesUpdatedRecordDh historiesDh, @MappingTarget HistoriesEntity historiesEntity);

    @Mapping(target = "loanId", source = "loan.loanId")
    HistoriesDto asDto(HistoriesEntity historiesEntity);

    List<HistoriesDto> asDtoList(List<HistoriesEntity> historiesEntity);

}
