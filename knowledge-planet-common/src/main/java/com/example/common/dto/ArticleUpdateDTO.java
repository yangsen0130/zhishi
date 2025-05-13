package com.example.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleUpdateDTO {
    private String title;
    private String content;
    private BigDecimal price;
    // Consider adding other fields that can be updated, e.g., status, tags, etc.
    // For partial updates, you might make these fields Optional or handle nulls in the service layer.
} 