// src/main/java/com/example/article/controller/ArticleController.java
package com.example.article.controller;

import com.example.article.service.ArticleService;
import com.example.common.dto.ArticleCreateDTO;
import com.example.common.dto.ArticleUpdateDTO;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.ArticleTitleVO;
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

    @PutMapping("/update/{articleId}")
    @Operation(summary = "修改文章")
    public Response<ArticleVO> updateArticle(@PathVariable Long articleId,
                                             @RequestBody ArticleUpdateDTO articleUpdateDTO,
                                             @RequestHeader("X-User-Id") Long userId) {
        ArticleVO articleVO = articleService.updateArticle(articleId, articleUpdateDTO, userId);
        return Response.success(articleVO);
    }

    @DeleteMapping("/delete/{articleId}")
    @Operation(summary = "删除文章")
    public Response<Void> deleteArticle(@PathVariable Long articleId,
                                        @RequestHeader("X-User-Id") Long userId) {
        articleService.deleteArticle(articleId, userId);
        return Response.success(null);
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
    @Operation(summary = "获取文章列表(仅标题)", description = "获取所有文章的标题列表，无需登录")
    public Response<List<ArticleTitleVO>> listArticles() {
        List<ArticleTitleVO> articleList = articleService.listArticles();
        return Response.success(articleList);
    }
}