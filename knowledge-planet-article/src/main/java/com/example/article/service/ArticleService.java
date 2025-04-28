// src/main/java/com/example/article/service/ArticleService.java
package com.example.article.service;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.vo.ArticleVO;

import java.util.List;

public interface ArticleService {
    /**
     * 创建文章
     */
    ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId);

    /**
     * 获取文章详情
     */
    ArticleVO getArticleDetail(Long articleId, Long userId);

    /**
     * 获取星球下的文章列表
     */
    List<ArticleVO> getArticlesByPlanetId(Long planetId, Long userId);
}