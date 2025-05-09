// src/main/java/com/example/article/controller/PlanetController.java
package com.example.article.controller;

import com.example.article.service.PlanetService;
import com.example.common.dto.PlanetCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.PlanetVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
@Tag(name = "星球接口", description = "处理星球相关请求")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping("/create")
    @Operation(summary = "创建星球")
    public Response<PlanetVO> createPlanet(@RequestBody PlanetCreateDTO planetDTO,
                                           @RequestHeader("X-User-Id") Long userId) {
        PlanetVO planetVO = planetService.createPlanet(planetDTO, userId);
        return Response.success(planetVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取星球列表")
    public Response<List<PlanetVO>> getPlanetList() {
        List<PlanetVO> planetList = planetService.getPlanetList();
        return Response.success(planetList);
    }

    @GetMapping("/detail/{planetId}")
    @Operation(summary = "获取星球详情")
    public Response<PlanetVO> getPlanetDetail(@PathVariable Long planetId) {
        PlanetVO planetVO = planetService.getPlanetDetail(planetId);
        return Response.success(planetVO);
    }

    @GetMapping("/access/{planetId}")
    @Operation(summary = "检查用户是否有权限访问星球")
    public Response<Boolean> checkUserPlanetAccess(@PathVariable Long planetId,
                                                   @RequestHeader("X-User-Id") Long userId) {
        boolean hasAccess = planetService.checkUserPlanetAccess(userId, planetId);
        return Response.success(hasAccess);
    }
}
