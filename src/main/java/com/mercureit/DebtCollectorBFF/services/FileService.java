package com.mercureit.DebtCollectorBFF.services;
import com.mercureit.DebtCollectorBFF.services.pieceJointe.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileService {
    @Autowired
    private MinioService minioService;
    private static final String FILE_PATH = "src/main/resources/test.drl";
    private static final String BACKUP_DIR = "src/main/resources/backup/";
    public String readFile() throws IOException {
        Path path = Paths.get(FILE_PATH);
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

    public void writeFile(String content) throws Exception {
        Path path = Paths.get(FILE_PATH);
        String currentContent = readFile();

        // Check if the content is different
        if (!currentContent.equals(content)) {
            // Save the current content as a backup
            saveBackup(currentContent);

            // Write the new content
            Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    private void saveBackup(String content) throws Exception {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String backupFileName = "backup_" + timestamp + ".drl";
        minioService.uploadFileForBackup(backupFileName, content);
    }

    public String getFileName() throws IOException {
        Path path = Paths.get(FILE_PATH);
        if (Files.exists(path)) {
            return path.getFileName().toString();
        } else {
            throw new IOException("File not found: " + FILE_PATH);
        }
    }
}
