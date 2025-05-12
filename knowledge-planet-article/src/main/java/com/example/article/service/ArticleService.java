// src/main/java/com/example/article/service/ArticleService.java
package com.example.article.service;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.ArticleTitleVO;

import java.util.List;

public interface ArticleService {
    /**
     * 创建文章
     * @param articleDTO Article data
     * @param authorId The ID of the user creating the article
     * @return Created Article VO
     */
    ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId);

    /**
     * 获取文章详情
     * @param articleId ID of the article
     * @param userId ID of the user requesting the article (for potential access checks)
     * @return Article VO
     */
    ArticleVO getArticleDetail(Long articleId, Long userId);

    /**
     * 获取文章列表 (No longer planet-specific, returns only titles)
     * @return List of ArticleTitleVOs
     */
    List<ArticleTitleVO> listArticles();
}