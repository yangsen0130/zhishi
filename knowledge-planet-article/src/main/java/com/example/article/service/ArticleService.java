// src/main/java/com/example/article/service/ArticleService.java
package com.example.article.service;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.dto.ArticleUpdateDTO;
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
     * 修改文章
     * @param articleId ID of the article to update
     * @param articleUpdateDTO Data for updating the article
     * @param userId ID of the user performing the update (for authorization)
     * @return Updated Article VO
     */
    ArticleVO updateArticle(Long articleId, ArticleUpdateDTO articleUpdateDTO, Long userId);

    /**
     * 删除文章
     * @param articleId ID of the article to delete
     * @param userId ID of the user performing the deletion (for authorization)
     */
    void deleteArticle(Long articleId, Long userId);

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
    
    /**
     * 获取指定用户发表的所有文章标题列表
     * @param authorId The ID of the author
     * @return List of article titles published by the specified user
     */
    List<ArticleTitleVO> getArticleTitlesByAuthor(Long authorId);
}