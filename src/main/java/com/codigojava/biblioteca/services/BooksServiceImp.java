package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.entities.BooksEntity;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.mappers.BooksMapper;
import com.codigojava.biblioteca.repositories.BooksRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BooksServiceImp implements BooksService {

    @NonNull
    private final BooksRepository booksRepository;

    @NonNull
    private final BooksMapper booksMapper;

    @Override
    public List<BooksDto> findAll() {
        final List<BooksEntity> booksList =
                this.booksRepository.findAll(Sort.by(Sort.Direction.ASC, "isbn"));

        if (CollectionUtils.isEmpty(booksList)) {
            log.warn("FindAll for books - There are not books in database");
            return Collections.emptyList();
        } else {
            return this.booksMapper.asDtoList(booksList);
        }
    }

    @Override
    public List<BooksPublicDto> findAllPublic() {
        final List<BooksEntity> booksList =
                this.booksRepository.findAll(Sort.by(Sort.Direction.ASC, "isbn"));

        if (CollectionUtils.isEmpty(booksList)) {
            log.warn("FindAllPublic for books - There are not books in database");
            return Collections.emptyList();
        } else {
            return this.booksMapper.asPublicDtoList(booksList);
        }
    }

    @Override
    public List<BooksPublicDto> findAllPrivate() {
        final List<BooksEntity> booksList =
                this.booksRepository.findAll(Sort.by(Sort.Direction.ASC, "isbn"));

        if (CollectionUtils.isEmpty(booksList)) {
            log.warn("FindAllPrivate for books - There are not books in database");
            return Collections.emptyList();
        } else {
            return this.booksMapper.asPrivateDtoList(booksList);
        }
    }

    public BooksDto findById(String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    public BooksPublicIsbnDto findByIdPublic(String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asPublicIsbnDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    public BooksPublicIsbnDto findByIdPrivate(String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asPublicIsbnDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

}
