// src/main/java/com/example/article/controller/ArticleController.java
package com.example.article.controller;

import com.example.article.service.ArticleService;
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Tag(name = "文章接口", description = "处理文章相关请求")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    @Operation(summary = "创建文章")
    public Response<ArticleVO> createArticle(@RequestBody ArticleCreateDTO articleDTO,
                                             @RequestHeader("X-User-Id") Long userId) {
        // userId is now the authorId implicitly
        ArticleVO articleVO = articleService.createArticle(articleDTO, userId);
        return Response.success(articleVO);
    }

    @GetMapping("/detail/{articleId}")
    @Operation(summary = "获取文章详情")
    public Response<ArticleVO> getArticleDetail(@PathVariable Long articleId,
                                                @RequestHeader("X-User-Id") Long userId) {
        // userId might be used for access control (e.g., checking purchase)
        ArticleVO articleVO = articleService.getArticleDetail(articleId, userId);
        return Response.success(articleVO);
    }

    // Renamed endpoint, removed planetId
    @GetMapping("/list")
    @Operation(summary = "获取文章列表")
    public Response<List<ArticleVO>> listArticles(@RequestHeader("X-User-Id") Long userId) {
        // userId might be used for filtering or context, but not planet access
        List<ArticleVO> articleList = articleService.listArticles(userId);
        return Response.success(articleList);
    }
}