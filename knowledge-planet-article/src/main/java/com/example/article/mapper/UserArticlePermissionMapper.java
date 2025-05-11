package com.example.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.UserArticlePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserArticlePermissionMapper extends BaseMapper<UserArticlePermission> {

    /**
     * Checks if a permission record exists for a specific order ID.
     * Used for idempotency check.
     * @param orderId The business order ID.
     * @return The count of matching records (should be 0 or 1 if order_id is unique).
     */
    @Select("SELECT COUNT(*) FROM user_article_permission WHERE order_id = #{orderId}")
    int countByOrderId(@Param("orderId") String orderId);

    /**
     * Checks if a permission exists for a given user and article.
     * Used for access control checks.
     * @param userId The user ID.
     * @param articleId The article ID.
     * @return The count of matching records (should be 0 or 1).
     */
    @Select("SELECT COUNT(*) FROM user_article_permission WHERE user_id = #{userId} AND article_id = #{articleId}")
    int countByUserAndArticle(@Param("userId") Long userId, @Param("articleId") Long articleId);

}