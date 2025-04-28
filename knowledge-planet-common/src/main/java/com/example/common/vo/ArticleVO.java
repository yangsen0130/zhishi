
// src/main/java/com/example/common/vo/ArticleVO.java
package com.example.common.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleVO {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName;
    private Long planetId;
    private String planetName;
    private LocalDateTime createTime;
}
