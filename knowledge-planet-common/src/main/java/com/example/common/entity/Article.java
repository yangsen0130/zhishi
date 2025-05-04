// src/main/java/com/example/common/entity/Article.java
package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("article") // ShardingSphere will route this to article_0, article_1, etc.
public class Article {
    @TableId(type = IdType.ASSIGN_ID) // Keep AUTO if sharding handles ID generation, otherwise consider other strategies
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    // Removed: private Long planetId;
    private Integer status; // 1-正常 0-删除
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}