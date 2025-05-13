// src/main/java/com/example/article/service/impl/ArticleServiceImpl.java
package com.example.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.ArticleMapper;
import com.example.article.service.ArticleService;
import com.example.article.service.PermissionService;
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.entity.Article;
import com.example.common.exception.BusinessException; // 仍然需要用于文章不存在等情况
import com.example.common.feign.AuthFeignClient;
import com.example.common.response.Code;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.UserVO;
import com.example.common.vo.ArticleTitleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal; // 确保导入
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
    private PermissionService permissionService;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthorId(authorId);
        article.setStatus(1);
        article.setPrice(articleDTO.getPrice());

        boolean saved = this.save(article);
        if (!saved) {
            log.error("Failed to save article for authorId: {}", authorId);
            throw new BusinessException(Code.SYSTEM_ERROR, "创建文章失败");
        }
        log.info("Article created with ID: {} by author: {}", article.getId(), authorId);

        // 对于新创建的文章，作者拥有完整权限
        ArticleVO vo = convertToVO(article, fetchAuthorDetails(Collections.singletonList(authorId)));
        if (vo != null) {
            vo.setHasFullAccess(true); // 作者总是有完整权限
        }
        return vo;
    }

    @Override
    public ArticleVO getArticleDetail(Long articleId, Long userId) {
        Article article = articleMapper.selectById(articleId);
        if (article == null || article.getStatus() != 1) {
            // 对于文章不存在的情况，仍然抛出业务异常
            throw new BusinessException(Code.ARTICLE_NOT_EXIST);
        }

        ArticleVO articleVO = new ArticleVO();
        BeanUtil.copyProperties(article, articleVO); // 复制 id, title, price 等基础信息

        // 获取作者信息并设置
        Map<Long, UserVO> userMap = fetchAuthorDetails(Collections.singletonList(article.getAuthorId()));
        if (userMap.containsKey(article.getAuthorId())) {
            articleVO.setAuthorName(userMap.get(article.getAuthorId()).getUsername());
        } else {
            articleVO.setAuthorName("未知作者");
        }
        articleVO.setAuthorId(article.getAuthorId()); // 确保 authorId 也被设置

        boolean isAuthor = article.getAuthorId().equals(userId);
        // 假设价格大于0的文章需要权限检查 (或者有其他字段标记是否付费)
        boolean requiresPermission = article.getPrice() != null && article.getPrice().compareTo(BigDecimal.ZERO) > 0;

        if (isAuthor || (requiresPermission && permissionService.hasPermission(userId, articleId)) || !requiresPermission) {
            // 情况1: 用户是作者
            // 情况2: 文章需要权限，且用户有权限
            // 情况3: 文章不需要权限 (免费文章)
            articleVO.setHasFullAccess(true);
            // content 已经被 BeanUtil.copyProperties 复制，这里无需额外操作
            log.info("Full access granted for userId: {} to articleId: {}", userId, articleId);
        } else {
            // 用户不是作者，文章需要权限，但用户没有权限
            articleVO.setHasFullAccess(false);
            articleVO.setContent(null); // 清空文章内容
            log.warn("Partial access for userId: {}. ArticleId: {}. User needs to purchase or gain permission.", userId, articleId);
        }

        return articleVO;
    }

    @Override
    public List<ArticleTitleVO> listArticles() {
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getTitle)
                .eq(Article::getStatus, 1)
                .orderByDesc(Article::getCreateTime));

        if (articles.isEmpty()) {
            return new ArrayList<>();
        }

        return articles.stream()
                .map(article -> new ArticleTitleVO(article.getId(), article.getTitle()))
                .collect(Collectors.toList());
    }

    private Map<Long, UserVO> fetchAuthorDetails(List<Long> authorIds) {
        Map<Long, UserVO> userMap = new java.util.HashMap<>();
        if (authorIds == null || authorIds.isEmpty()) {
            return userMap;
        }
        for (Long authorId : authorIds) {
            if (authorId == null) continue;
            try {
                Response<UserVO> userResponse = authFeignClient.getUserInfo(authorId);
                if (userResponse != null && userResponse.getCode() == 200 && userResponse.getData() != null) {
                    userMap.put(authorId, userResponse.getData());
                } else {
                    log.warn("Could not fetch user info for authorId: {}. Response: {}", authorId, userResponse);
                }
            } catch (Exception e) {
                log.error("Error fetching user info for authorId: {}", authorId, e);
            }
        }
        return userMap;
    }

    // convertToVO 辅助方法现在主要由 BeanUtil.copyProperties 完成，
    // 并且权限相关的逻辑移到了 getArticleDetail 中。
    // 如果 ArticleVO 和 Article 字段完全一致，此方法可以简化或内联。
    // 这里保留是为了清晰地处理作者名称的获取。
    private ArticleVO convertToVO(Article article, Map<Long, UserVO> userMap) {
        if (article == null) return null;
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article, vo); // price 字段也会被复制

        if (article.getAuthorId() != null) {
            if (userMap != null && userMap.containsKey(article.getAuthorId())) {
                vo.setAuthorName(userMap.get(article.getAuthorId()).getUsername());
            } else {
                try {
                    Response<UserVO> userResponse = authFeignClient.getUserInfo(article.getAuthorId());
                    if (userResponse != null && userResponse.getCode() == 200 && userResponse.getData() != null) {
                        vo.setAuthorName(userResponse.getData().getUsername());
                    } else {
                        vo.setAuthorName("未知作者");
                        log.warn("Could not fetch user info for authorId: {} in convertToVO. Response: {}", article.getAuthorId(), userResponse);
                    }
                } catch (Exception e) {
                    vo.setAuthorName("未知作者");
                    log.error("Error fetching user info for authorId: {} in convertToVO", article.getAuthorId(), e);
                }
            }
        } else {
            vo.setAuthorName("未知作者");
        }
        return vo;
    }
}