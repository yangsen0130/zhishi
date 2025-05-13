// src/main/java/com/example/article/mapper/ArticleMapper.java
package com.example.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    // Add this method
    @Select("SELECT id, title FROM article WHERE status = #{status} ORDER BY create_time DESC")
    List<Article> selectIdAndTitleByStatusOrderByCreateTimeDesc(@Param("status") Integer status);
    
    @Select("SELECT id, title FROM article WHERE author_id = #{authorId} AND status = #{status} ORDER BY create_time DESC")
    List<Article> selectIdAndTitleByAuthorIdAndStatusOrderByCreateTimeDesc(@Param("authorId") Long authorId, @Param("status") Integer status);
}