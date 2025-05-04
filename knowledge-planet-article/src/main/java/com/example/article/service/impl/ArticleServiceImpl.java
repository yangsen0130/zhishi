// src/main/java/com/example/article/service/impl/ArticleServiceImpl.java
package com.example.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.ArticleMapper;
import com.example.article.service.ArticleService;
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.entity.Article;
import com.example.common.exception.BusinessException; // Import exception
import com.example.common.feign.AuthFeignClient;
// Removed: import com.example.common.feign.OrderFeignClient; // If needed for purchase check
import com.example.common.response.Code; // Import Code enum
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    // Removed: PlanetService dependency
    // @Autowired
    // private PlanetService planetService;

    @Autowired
    private AuthFeignClient authFeignClient;

    // Optional: Inject OrderFeignClient if you need to check if user purchased the article
    // @Autowired
    // private OrderFeignClient orderFeignClient;

    @Override
    public ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId) {
        // Removed: Planet access check

        // Create article
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthorId(authorId);
        // Removed: article.setPlanetId(articleDTO.getPlanetId());
        article.setStatus(1); // 1 = Active/Normal

        // Save article
        boolean saved = this.save(article);
        if (!saved) {
            log.error("Failed to save article for authorId: {}", authorId);
            throw new BusinessException(Code.SYSTEM_ERROR, "创建文章失败");
        }
        log.info("Article created with ID: {} by author: {}", article.getId(), authorId);


        // Return article information
        return convertToVO(article);
    }

    @Override
    public ArticleVO getArticleDetail(Long articleId, Long userId) {
        Article article = this.getById(articleId);
        if (article == null || article.getStatus() != 1) {
            throw new BusinessException(Code.ARTICLE_NOT_EXIST);
        }

        // Removed: Planet access check
        // Optional: Implement new access control logic here.
        // For example, check if the article is free or if the user has purchased it.
        // boolean hasAccess = checkArticleAccess(userId, article);
        // if (!hasAccess) {
        //     throw new BusinessException(Code.ARTICLE_ACCESS_DENIED);
        // }

        // Return article information
        return convertToVO(article);
    }

    @Override
    public List<ArticleVO> listArticles(Long userId) {
        // Removed: Planet access check for the list itself

        // Query active article list
        List<Article> articles = this.list(new LambdaQueryWrapper<Article>()
                // Removed: .eq(Article::getPlanetId, planetId)
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

        // Fetch author information in bulk if possible, or individually
        Map<Long, UserVO> userMap = new java.util.HashMap<>();
        // Consider a bulk fetch method in AuthFeignClient if available
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

        // Convert to VO list
        return articles.stream().map(article -> {
            ArticleVO vo = convertToVOWithoutDetails(article); // Use a potentially lighter converter for lists
            // Set author name from the fetched map
            UserVO author = userMap.get(article.getAuthorId());
            vo.setAuthorName(author != null ? author.getUsername() : "未知作者");
            return vo;
        }).collect(Collectors.toList());
    }

    // Helper to convert Article to ArticleVO
    private ArticleVO convertToVO(Article article) {
        if (article == null) return null;
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article, vo);

        // Fetch author information
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


        // Removed: Fetching planet information
        // try {
        //     vo.setPlanetName(planetService.getPlanetDetail(article.getPlanetId()).getName());
        // } catch (Exception e) {
        //     vo.setPlanetName("未知星球");
        // }

        return vo;
    }

    // Lighter converter for list view, avoids redundant calls if author name is set separately
    private ArticleVO convertToVOWithoutDetails(Article article) {
        if (article == null) return null;
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article, vo);
        // Author name will be set later from the map
        return vo;
    }

    // Optional: Placeholder for new access control logic
    // private boolean checkArticleAccess(Long userId, Article article) {
    //     // 1. Check if the article requires purchase (you might need a field in Article entity)
    //     // if (!article.isPaid()) return true; // Example: Free article
    //
    //     // 2. Check if the user is the author
    //     // if (article.getAuthorId().equals(userId)) return true;
    //
    //     // 3. Check if the user has purchased the article via OrderFeignClient
    //     // try {
    //     //     Response<Boolean> orderCheckResponse = orderFeignClient.checkUserArticleOrder(userId, article.getId());
    //     //     return orderCheckResponse != null && orderCheckResponse.getCode() == 200 && Boolean.TRUE.equals(orderCheckResponse.getData());
    //     // } catch (Exception e) {
    //     //     log.error("Error checking article purchase for userId: {}, articleId: {}", userId, article.getId(), e);
    //     //     return false; // Fail safe
    //     // }
    //     return true; // Defaulting to accessible for now
    // }
}