package com.codigojava.biblioteca.mappers;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.entities.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BooksMapper {

    BooksEntity asEntity(BooksRecordDh booksDh);

    List<BooksEntity> asEntityList(List<BooksRecordDh> booksDh);

    @Mapping(target = "authorId", source = "author.authorId")
    @Mapping(target = "publisherId", source = "publisher.publisherId")
    @Mapping(target = "categoryId", source = "category.categoryId")
    BooksDto asDto(BooksEntity books);

    @Mapping(target = "nameAuthor", source = "author.nameAuthor")
    @Mapping(target = "namePublisher", source = "publisher.namePublisher")
    @Mapping(target = "nameCategory", source = "category.nameCategory")
    @Mapping(target = "subtopicCategory", source = "category.subtopicCategory")
    BooksPublicDto asPublicDto(BooksEntity books);

    @Mapping(target = "nameAuthor", source = "author.nameAuthor")
    @Mapping(target = "namePublisher", source = "publisher.namePublisher")
    @Mapping(target = "nameCategory", source = "category.nameCategory")
    @Mapping(target = "subtopicCategory", source = "category.subtopicCategory")
    BooksPublicIsbnDto asPublicIsbnDto(BooksEntity books);

    BooksFileDto asFileDto(BooksEntity books);

    List<BooksDto> asDtoList(List<BooksEntity> booksLists);

    List<BooksPublicDto> asPublicDtoList(List<BooksEntity> booksLists);

    List<BooksPublicDto> asPrivateDtoList(List<BooksEntity> booksLists);

}
