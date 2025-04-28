// src/main/java/com/example/common/feign/ArticleFeignClient.java
package com.example.common.feign;

import com.example.common.dto.ArticleCreateDTO;
import com.example.common.result.Result;
import com.example.common.vo.ArticleVO;
import com.example.common.vo.PlanetVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "knowledge-planet-article")
public interface ArticleFeignClient {
    @GetMapping("/article/planet/{planetId}")
    Result<List<ArticleVO>> getArticlesByPlanetId(@PathVariable("planetId") Long planetId);

    @GetMapping("/article/detail/{articleId}")
    Result<ArticleVO> getArticleDetail(@PathVariable("articleId") Long articleId,
                                       @RequestHeader("X-User-Id") Long userId);

    @PostMapping("/article/create")
    Result<ArticleVO> createArticle(@RequestBody ArticleCreateDTO articleDTO,
                                    @RequestHeader("X-User-Id") Long userId);

    @GetMapping("/planet/detail/{planetId}")
    Result<PlanetVO> getPlanetDetail(@PathVariable("planetId") Long planetId);

    @GetMapping("/planet/list")
    Result<List<PlanetVO>> getPlanetList();

    @GetMapping("/planet/access/{planetId}")
    Result<Boolean> checkUserPlanetAccess(@PathVariable("planetId") Long planetId,
                                          @RequestHeader("X-User-Id") Long userId);
}