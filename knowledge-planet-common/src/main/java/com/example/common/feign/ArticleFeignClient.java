// src/main/java/com/example/common/feign/ArticleFeignClient.java
package com.example.common.feign;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.PlanetVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "knowledge-planet-article")
public interface ArticleFeignClient {
    @GetMapping("/article/planet/{planetId}")
    Response<List<ArticleVO>> getArticlesByPlanetId(@PathVariable("planetId") Long planetId);

    @GetMapping("/article/detail/{articleId}")
    Response<ArticleVO> getArticleDetail(@PathVariable("articleId") Long articleId,
                                         @RequestHeader("X-User-Id") Long userId);

    @PostMapping("/article/create")
    Response<ArticleVO> createArticle(@RequestBody ArticleCreateDTO articleDTO,
                                      @RequestHeader("X-User-Id") Long userId);

    @GetMapping("/planet/detail/{planetId}")
    Response<PlanetVO> getPlanetDetail(@PathVariable("planetId") Long planetId);

    @GetMapping("/planet/list")
    Response<List<PlanetVO>> getPlanetList();

    @GetMapping("/planet/access/{planetId}")
    Response<Boolean> checkUserPlanetAccess(@PathVariable("planetId") Long planetId,
                                            @RequestHeader("X-User-Id") Long userId);
}