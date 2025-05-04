// src/main/java/com/example/common/vo/ArticleVO.java
package com.example.common.vo;

import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer; // 导入 ToStringSerializer

@Data
public class ArticleVO {
    @JsonSerialize(using = ToStringSerializer.class) // <--- 添加注解 (如果 authorId 也可能很大)
    private Long id;
    private String title;
    private String content;
    @JsonSerialize(using = ToStringSerializer.class) // <--- 添加注解 (如果 authorId 也可能很大)
    private Long authorId;
    private String authorName;
    // Removed: private Long planetId;
    // Removed: private String planetName;
    private LocalDateTime createTime;
}