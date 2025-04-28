// src/main/java/com/example/common/dto/ArticleCreateDTO.java
package com.example.common.dto;

import lombok.Data;

@Data
public class ArticleCreateDTO {
    private String title;
    private String content;
    private Long planetId;
}