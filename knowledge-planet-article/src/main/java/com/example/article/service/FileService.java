// src/main/java/com/example/article/service/FileService.java
package com.example.article.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * Upload file to MinIO
     * @param file File to upload
     * @return File URL
     */
    String uploadFile(MultipartFile file);

    /**
     * Delete file from MinIO
     * @param fileUrl File URL to delete
     */
    void deleteFile(String fileUrl);
}