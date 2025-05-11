// src/main/java/com/example/article/service/impl/ArticleServiceImpl.java
package com.example.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.ArticleMapper;
import com.example.article.service.ArticleService;
import com.example.article.service.PermissionService; // *** ADDED ***
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.entity.Article;
import com.example.common.exception.BusinessException;
import com.example.common.feign.AuthFeignClient;
import com.example.common.response.Code;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private AuthFeignClient authFeignClient;

    @Autowired
    private PermissionService permissionService; // *** ADDED: Inject PermissionService ***

    // *** Assume Article entity has a 'isPaid' boolean field, or similar mechanism ***
    // Example: Add `private Boolean isPaid = true;` to Article.java entity

    @Override
    public ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId) {
        // Create article
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthorId(authorId);
        article.setStatus(1); // 1 = Active/Normal
        // article.setIsPaid(true); // Example: Mark as requiring payment

        boolean saved = this.save(article);
        if (!saved) {
            log.error("Failed to save article for authorId: {}", authorId);
            throw new BusinessException(Code.SYSTEM_ERROR, "创建文章失败");
        }
        log.info("Article created with ID: {} by author: {}", article.getId(), authorId);
        return convertToVO(article, null); // Pass null for userMap initially
    }

    @Override
    public ArticleVO getArticleDetail(Long articleId, Long userId) {
        Article article = this.getById(articleId);
        if (article == null || article.getStatus() != 1) {
            throw new BusinessException(Code.ARTICLE_NOT_EXIST);
        }

        // --- Access Control Check ---
        // 1. Is the user the author? Authors always have access.
        boolean isAuthor = article.getAuthorId().equals(userId);

        // 2. Does the article require payment/permission? (Assume a field like 'isPaid')
        // boolean requiresPermission = article.getIsPaid(); // Example check
        boolean requiresPermission = true; // Let's assume all articles require permission for now

        // 3. If it requires permission and user is not the author, check permission table
        boolean hasPermission = false;
        if (!isAuthor && requiresPermission) {
            hasPermission = permissionService.hasPermission(userId, articleId);
        }

        // 4. Deny access if required permission is missing
        if (!isAuthor && requiresPermission && !hasPermission) {
            log.warn("Access denied for userId: {} to articleId: {}", userId, articleId);
            throw new BusinessException(Code.ARTICLE_ACCESS_DENIED, "您需要购买才能阅读此文章");
        }

        log.info("Access granted for userId: {} to articleId: {}", userId, articleId);

        // Fetch author details for the VO
        Map<Long, UserVO> userMap = fetchAuthorDetails(Collections.singletonList(article.getAuthorId()));

        return convertToVO(article, userMap);
    }

    @Override
    public List<ArticleVO> listArticles(Long userId) {
        // Query active article list
        List<Article> articles = this.list(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, 1) // Only show active articles
                .orderByDesc(Article::getCreateTime));

        if (articles.isEmpty()) {
            return new ArrayList<>();
        }

        // Get distinct author IDs
        List<Long> authorIds = articles.stream()
                .map(Article::getAuthorId)
                .distinct()
                .collect(Collectors.toList());

        // Fetch author information in bulk
        Map<Long, UserVO> userMap = fetchAuthorDetails(authorIds);

        // Convert to VO list
        return articles.stream()
                .map(article -> convertToVO(article, userMap)) // Use the main converter
                .collect(Collectors.toList());
    }

    // Helper to fetch author details
    private Map<Long, UserVO> fetchAuthorDetails(List<Long> authorIds) {
        Map<Long, UserVO> userMap = new java.util.HashMap<>();
        if (authorIds == null || authorIds.isEmpty()) {
            return userMap;
        }
        // Consider a bulk fetch method in AuthFeignClient if available
        // For now, fetch individually
        for (Long authorId : authorIds) {
            try {
                Response<UserVO> userResponse = authFeignClient.getUserInfo(authorId);
                if (userResponse != null && userResponse.getCode() == 200 && userResponse.getData() != null) {
                    userMap.put(authorId, userResponse.getData());
                } else {
                    log.warn("Could not fetch user info for authorId: {}", authorId);
                }
            } catch (Exception e) {
                log.error("Error fetching user info for authorId: {}", authorId, e);
            }
        }
        return userMap;
    }

    // Helper to convert Article to ArticleVO, using pre-fetched author map
    private ArticleVO convertToVO(Article article, Map<Long, UserVO> userMap) {
        if (article == null) return null;
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article, vo);

        // Set author name from the fetched map
        if (userMap != null && userMap.containsKey(article.getAuthorId())) {
            vo.setAuthorName(userMap.get(article.getAuthorId()).getUsername());
        } else if (userMap == null) { // Fetch individually if map not provided (e.g., single detail view)
            try {
                Response<UserVO> userResponse = authFeignClient.getUserInfo(article.getAuthorId());
                if (userResponse != null && userResponse.getCode() == 200 && userResponse.getData() != null) {
                    vo.setAuthorName(userResponse.getData().getUsername());
                } else {
                    vo.setAuthorName("未知作者");
                    log.warn("Could not fetch user info for authorId: {} in convertToVO", article.getAuthorId());
                }
            } catch (Exception e) {
                vo.setAuthorName("未知作者");
                log.error("Error fetching user info for authorId: {} in convertToVO", article.getAuthorId(), e);
            }
        }
        else {
            vo.setAuthorName("未知作者"); // Author not found in map
        }

        return vo;
    }
}