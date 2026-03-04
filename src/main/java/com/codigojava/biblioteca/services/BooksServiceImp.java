package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.BooksRecordDh;
import com.codigojava.biblioteca.dtos.BooksDto;
import com.codigojava.biblioteca.dtos.BooksFileDto;
import com.codigojava.biblioteca.dtos.BooksPublicDto;
import com.codigojava.biblioteca.dtos.BooksPublicIsbnDto;
import com.codigojava.biblioteca.entities.BooksEntity;
import com.codigojava.biblioteca.exceptions.BdInternalException;
import com.codigojava.biblioteca.exceptions.BdNotFoundException;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.BooksMapper;
import com.codigojava.biblioteca.repositories.AuthorsRepository;
import com.codigojava.biblioteca.repositories.BooksRepository;
import com.codigojava.biblioteca.repositories.CategoriesRepository;
import com.codigojava.biblioteca.repositories.PublishersRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
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
    private final AuthorsRepository authorsRepository;

    @NonNull
    private final CategoriesRepository categoriesRepository;

    @NonNull
    private final PublishersRepository publishersRepository;

    @NonNull
    private final BooksMapper booksMapper;

    @NonNull
    private final FileStorageService fileStorageService;

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

    @Override
    public BooksDto findById(final String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    @Override
    public BooksPublicIsbnDto findByIdPublic(final String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asPublicIsbnDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    @Override
    public BooksPublicIsbnDto findByIdPrivate(final String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asPublicIsbnDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    @Override
    public List<BooksPublicDto> findByName(final String name) {
        final List<BooksEntity> booksList =
                this.booksRepository.findByTitleContainingIgnoreCase(name);

        if (CollectionUtils.isEmpty(booksList)) {
            log.warn("FindByName for books - There are not books in database with title: {}", name);
            return Collections.emptyList();
        } else {
            return this.booksMapper.asPublicDtoList(booksList);
        }
    }

    @Override
    public BooksFileDto findFileById(final String isbn) {
        final Optional<BooksEntity> bookOptional = this.booksRepository.findById(isbn);

        if (bookOptional.isPresent()) {
            return this.booksMapper.asFileDto(bookOptional.get());
        } else {
            throw new BdNotFoundException("GET - There is not books in the database with the isbn: " + isbn);
        }
    }

    @Override
    public BooksDto save(final BooksRecordDh bookDh) {
        final BooksEntity books = this.booksMapper.asEntity(bookDh);

        // Asignar relaciones ManyToOne con
        books.setAuthor(
                authorsRepository.findById(bookDh.authorId())
                        .orElseThrow(() -> new BdNotSaveException("Author not found"))
        );
        books.setPublisher(
                publishersRepository.findById(bookDh.publisherId())
                        .orElseThrow(() -> new BdNotSaveException("Publisher not found"))
        );
        books.setCategory(
                categoriesRepository.findById(bookDh.categoryId())
                        .orElseThrow(() -> new BdNotSaveException("Category not found"))
        );

        try {
            final BooksEntity savedBook = this.booksRepository.save(books);
            return this.booksMapper.asDto(savedBook);
        } catch (DataIntegrityViolationException e) {
            log.warn("Save for books - Integrity violation: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error saving books.  Possible cause: duplicated data or constraint violation.");
        } catch (Exception e) {
            log.warn("Save for books - Error saving book. Possible cause: {}", e.getMessage());
            throw new BdNotSaveException("POST - Error save book.  Possible cause: BD inconsistency or internal failure.");
        }
    }

    @Override
    public BooksDto updateById(final String isbn, final BooksRecordDh bookDh) {

        BooksEntity existingBook = booksRepository.findById(isbn)
                .orElseThrow(() -> new BdNotFoundException("PUT - No book not found with isbn: " + isbn));

        if (bookDh.isbn() != null && !bookDh.isbn().equals(isbn)) {
            throw new BdNotSaveException(
                    "PUT - Parameters are incorrect: isbn " + bookDh.isbn() + " is different from id " + isbn);
        }

        // MapStruct copia campos simples
        booksMapper.updateEntityFromDh(bookDh, existingBook);

        // Asignar relaciones ManyToOne
        existingBook.setAuthor(
                authorsRepository.findById(bookDh.authorId())
                        .orElseThrow(() -> new BdNotFoundException("PUT - Author not found with id: " + bookDh.authorId()))
        );

        existingBook.setPublisher(
                publishersRepository.findById(bookDh.publisherId())
                        .orElseThrow(() -> new BdNotFoundException("PUT - Publisher not found with id: " + bookDh.publisherId()))
        );

        existingBook.setCategory(
                categoriesRepository.findById(bookDh.categoryId())
                        .orElseThrow(() -> new BdNotFoundException("PUT - Category not found with id: " + bookDh.categoryId()))
        );

        try {
            final BooksEntity updatedBook = this.booksRepository.save(existingBook);

            return this.booksMapper.asDto(updatedBook);
        } catch (Exception e) {
            throw new BdInternalException(
                    "PUT - Error saving book. Possible cause: DB inconsistency or internal failure."
            );
        }

    }

    @Override
    public BooksDto updateFiles(String isbn, MultipartFile bookCover, MultipartFile bookFile) {

        // 1. Validar que el libro existe
        BooksEntity book = booksRepository.findById(isbn)
                .orElseThrow(() -> new BdNotFoundException("PUT FILES -No book found with isbn: " + isbn));

        // 2. Validar que vienen los dos ficheros o ninguno
        if ((bookCover == null || bookCover.isEmpty()) ||
                (bookFile == null || bookFile.isEmpty())) {
            throw new BdNotSaveException("PUT FILES - Both cover and book file must be provided.");
        }

        // 3. Asegurar que los directorios existen
        fileStorageService.ensureDirectories();

        // 4. Borrar ficheros antiguos (si existen)
        if (book.getBookCover() != null) {
            Path oldCoverPath = Paths.get(book.getBookCover());
            fileStorageService.deleteIfExists(oldCoverPath);
        }

        if (book.getBookFile() != null) {
            Path oldFilePath = Paths.get(book.getBookFile());
            fileStorageService.deleteIfExists(oldFilePath);
        }

        // 5. Validar extensiones
        fileStorageService.validateCoverExtension(bookCover.getOriginalFilename());
        fileStorageService.validateBookExtension(bookFile.getOriginalFilename());

        // 6. Construcción de nombres
        String coverFileName = isbn + "_" + bookCover.getOriginalFilename();
        String pdfFileName = isbn + "_" + bookFile.getOriginalFilename();


        // 7. Guardar físico de los nuevos ficheros
        String savedCoverPath = fileStorageService.saveFile(
                bookCover,
                fileStorageService.getCoverDir(),
                coverFileName
        );

        String savedPdfPath = fileStorageService.saveFile(
                bookFile,
                fileStorageService.getFileDir(),
                pdfFileName
        );


        // 8. Actualizar la entidad con las nuevas rutas
        book.setBookCover(savedCoverPath);
        book.setBookFile(savedPdfPath);


        // 9. Guardar en BD y devolver DTO
        BooksEntity saved = booksRepository.save(book);
        return booksMapper.asDto(saved);
    }

}
