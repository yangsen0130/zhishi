package com.example.article.controller;

import com.example.article.service.FileService;
import com.example.common.exception.BusinessException;
import com.example.common.response.Code;
import com.example.common.response.Response;
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
    public Response<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("X-User-Id") Long userId) {

        // Validate file
        if (file.isEmpty()) {
            throw new BusinessException(Code.FILE_EMPTY);
        }

        // Check content type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new BusinessException(Code.FILE_TYPE_ERROR, "只能上传图片文件");
        }

        // Upload file
        String fileUrl = fileService.uploadFile(file);

        // Return result
        Map<String, String> data = new HashMap<>();
        data.put("url", fileUrl);
        return Response.success(data);
    }
}