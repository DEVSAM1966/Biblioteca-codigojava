package com.codigojava.biblioteca.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileStorageService {

    private static final String COVER_DIR = "uploads/cover";
    private static final String FILE_DIR = "uploads/file";

    // Asegura que los directorios existen
    public void ensureDirectories() {
        try {
            Files.createDirectories(Paths.get(COVER_DIR));
            Files.createDirectories(Paths.get(FILE_DIR));
        } catch (IOException e) {
            log.error("Error creating upload directories", e);
            throw new RuntimeException("Could not create upload directories", e);
        }
    }

    // Obtiene la extensión de un nombre de fichero
    public String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1);
    }

    // Valida extensión de portada (jpg/jpeg)
    public void validateCoverExtension(String filename) {
        String ext = getExtension(filename).toLowerCase();
        if (!ext.equals("jpg") && !ext.equals("jpeg")) {
            throw new IllegalArgumentException("Cover file must be JPG or JPEG");
        }
    }

    // Valida extensión de libro (pdf)
    public void validateBookExtension(String filename) {
        String ext = getExtension(filename).toLowerCase();
        if (!ext.equals("pdf")) {
            throw new IllegalArgumentException("Book file must be PDF");
        }
    }

    // Borra un fichero de forma segura (si no existe, solo warning)
    public void deleteIfExists(Path path) {
        try {
            boolean deleted = Files.deleteIfExists(path);
            if (!deleted) {
                log.warn("File not found for deletion: {}", path);
            }
        } catch (IOException e) {
            log.warn("Error deleting file: {}", path, e);
        }
    }

    // Guarda un fichero en disco y devuelve la ruta relativa
    public String saveFile(MultipartFile file, String baseDir, String targetFileName) {
        try {
            Path dirPath = Paths.get(baseDir);
            Files.createDirectories(dirPath);

            Path targetPath = dirPath.resolve(targetFileName);
            Files.write(targetPath, file.getBytes());

            return baseDir + "/" + targetFileName;
        } catch (IOException e) {
            log.error("Error saving file {}", targetFileName, e);
            throw new RuntimeException("Error saving file " + targetFileName, e);
        }
    }

    public String getCoverDir() {
        return COVER_DIR;
    }

    public String getFileDir() {
        return FILE_DIR;
    }
}

