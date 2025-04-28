
// src/main/java/com/example/article/service/impl/ArticleServiceImpl.java
package com.example.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.ArticleMapper;
import com.example.article.service.ArticleService;
import com.example.article.service.PlanetService;
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.entity.Article;
import com.example.common.feign.AuthFeignClient;
import com.example.common.result.Result;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public ArticleVO createArticle(ArticleCreateDTO articleDTO, Long authorId) {
        // 检查用户是否有权限在该星球发布文章
        if (!planetService.checkUserPlanetAccess(authorId, articleDTO.getPlanetId())) {
            throw new RuntimeException("您不是该星球成员，无法发布文章");
        }

        // 创建文章
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthorId(authorId);
        article.setPlanetId(articleDTO.getPlanetId());
        article.setStatus(1);

        // 保存文章
        this.save(article);

        // 返回文章信息
        ArticleVO articleVO = convertToVO(article);
        return articleVO;
    }

    @Override
    public ArticleVO getArticleDetail(Long articleId, Long userId) {
        Article article = this.getById(articleId);
        if (article == null || article.getStatus() != 1) {
            throw new RuntimeException("文章不存在或已删除");
        }

        // 检查用户是否有权限查看该文章
        if (!planetService.checkUserPlanetAccess(userId, article.getPlanetId())) {
            throw new RuntimeException("您不是该星球成员，无法查看文章");
        }

        // 返回文章信息
        return convertToVO(article);
    }

    @Override
    public List<ArticleVO> getArticlesByPlanetId(Long planetId, Long userId) {
        // 检查用户是否有权限查看该星球的文章
        if (!planetService.checkUserPlanetAccess(userId, planetId)) {
            // 用户没有权限，返回空列表
            return new ArrayList<>();
        }

        // 查询文章列表
        List<Article> articles = this.list(new LambdaQueryWrapper<Article>()
                .eq(Article::getPlanetId, planetId)
                .eq(Article::getStatus, 1)
                .orderByDesc(Article::getCreateTime));

        // 获取所有作者信息
        List<Long> authorIds = articles.stream()
                .map(Article::getAuthorId)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, UserVO> userMap = new java.util.HashMap<>();
        for (Long authorId : authorIds) {
            Result<UserVO> userResult = authFeignClient.getUserInfo(authorId);
            if (userResult.getCode() == 200 && userResult.getData() != null) {
                userMap.put(authorId, userResult.getData());
            }
        }

        // 转换为VO
        return articles.stream().map(article -> {
            ArticleVO vo = convertToVO(article);
            // 设置作者名称
            UserVO author = userMap.get(article.getAuthorId());
            if (author != null) {
                vo.setAuthorName(author.getUsername());
            }
            return vo;
        }).collect(Collectors.toList());
    }

    private ArticleVO convertToVO(Article article) {
        ArticleVO vo = new ArticleVO();
        BeanUtil.copyProperties(article, vo);

        // 获取作者信息
        Result<UserVO> userResult = authFeignClient.getUserInfo(article.getAuthorId());
        if (userResult.getCode() == 200 && userResult.getData() != null) {
            vo.setAuthorName(userResult.getData().getUsername());
        }

        // 获取星球信息
        try {
            vo.setPlanetName(planetService.getPlanetDetail(article.getPlanetId()).getName());
        } catch (Exception e) {
            vo.setPlanetName("未知星球");
        }

        return vo;
    }
}