package com.example.ala.File;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank()) {
            log.warn("fileUrl is either null or blank.");
            return null;
        }
        try {
            Path filePath = new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException exception) {
            log.warn(String.format("No file found at the path: %s", fileUrl), exception);
        }
        return null;
    }

}
