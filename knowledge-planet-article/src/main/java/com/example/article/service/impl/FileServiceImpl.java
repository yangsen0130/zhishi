// src/main/java/com/example/article/service/impl/FileServiceImpl.java
package com.example.article.service.impl;

import com.example.article.config.MinioConfig;
import com.example.article.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // Generate file path with date structure (yyyy/MM/dd)
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            // Get original filename
            String originalFilename = file.getOriginalFilename();
            // Generate unique filename
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") +
                    getFileExtension(originalFilename);

            // Final path: yyyy/MM/dd/uuid.extension
            String objectName = datePath + "/" + fileName;

            // Upload to MinIO
            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(minioConfig.getBucketName())
                                .object(objectName)
                                .stream(inputStream, file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build());
            }

            // Return the complete URL to the file
            return minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/" + objectName;
        } catch (Exception e) {
            log.error("File upload failed", e);
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String fileUrl) {
        try {
            // Extract object name from URL
            String objectName = fileUrl.substring(
                    (minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/").length());

            // Delete from MinIO
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(objectName)
                            .build());
        } catch (Exception e) {
            log.error("File deletion failed", e);
            throw new RuntimeException("File deletion failed: " + e.getMessage());
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}