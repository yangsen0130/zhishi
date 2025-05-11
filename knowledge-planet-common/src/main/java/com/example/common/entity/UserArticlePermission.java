package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_article_permission")
public class UserArticlePermission {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long articleId;
    private String orderId; // Link to the order that granted permission (IMPORTANT for idempotency)
    private LocalDateTime grantTime; // When the permission was granted

    // Add a UNIQUE constraint on 'order_id' in your database schema
    // ALTER TABLE user_article_permission ADD CONSTRAINT uk_order_id UNIQUE (order_id);
    // OR a unique constraint on (user_id, article_id) if a user can only buy an article once
    // ALTER TABLE user_article_permission ADD CONSTRAINT uk_user_article UNIQUE (user_id, article_id);
}