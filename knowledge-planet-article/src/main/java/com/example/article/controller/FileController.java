// src/main/java/com/example/article/controller/FileController.java
package com.example.article.controller;

import com.example.article.service.FileService;
import com.example.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Tag(name = "文件接口", description = "处理文件上传下载相关请求")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileupload")
    @Operation(summary = "上传图片")
    public Result<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("X-User-Id") Long userId) {

        // Validate file
        if (file.isEmpty()) {
            return Result.error("上传文件不能为空");
        }

        // Check content type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return Result.error("只能上传图片文件");
        }

        // Upload file
        String fileUrl = fileService.uploadFile(file);

        // Return result
        Map<String, String> data = new HashMap<>();
        data.put("url", fileUrl);
        return Result.success(data);
    }
}