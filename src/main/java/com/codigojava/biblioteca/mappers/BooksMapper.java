package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.entities.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BooksMapper {

    BooksEntity asEntity(BooksRecordDh booksDh);

    List<BooksEntity> asEntityList(List<BooksRecordDh> booksDh);

    BooksDto asDto(BooksEntity books);

    BooksPublicDto asPublicDto(BooksEntity books);

    BooksPublicIsbnDto asIsbnDto(BooksEntity books);

    BooksFileDto asFileDto(BooksEntity books);

    List<BooksDto> asDtoList(List<BooksEntity> booksLists);
}
