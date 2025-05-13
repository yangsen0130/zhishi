// src/main/java/com/example/common/dto/ArticleCreateDTO.java
package com.example.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleCreateDTO {
    private String title;
    private String content;
    private BigDecimal price;

}