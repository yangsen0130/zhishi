// src/main/java/com/example/common/feign/ArticleFeignClient.java
package com.example.common.feign;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.ArticleTitleVO;
// Removed: import com.example.common.vo.PlanetVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "knowledge-planet-article")
public interface ArticleFeignClient {

    @GetMapping("/article/list")
    Response<List<ArticleTitleVO>> listArticles(); // Removed userId, changed return type

    @GetMapping("/article/detail/{articleId}")
    Response<ArticleVO> getArticleDetail(@PathVariable("articleId") Long articleId,
                                         @RequestHeader("X-User-Id") Long userId); // Keep userId for potential access control

    @PostMapping("/article/create")
    Response<ArticleVO> createArticle(@RequestBody ArticleCreateDTO articleDTO,
                                      @RequestHeader("X-User-Id") Long userId);
}