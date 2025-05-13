// src/main/java/com/example/common/entity/Article.java
package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("article")
public class Article {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Integer status; // 1-正常 0-删除
    private BigDecimal price;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}