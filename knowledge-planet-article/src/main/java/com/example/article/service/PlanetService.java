// src/main/java/com/example/article/service/PlanetService.java
package com.example.article.service;

import com.example.common.dto.PlanetCreateDTO;
import com.example.common.entity.Planet;
import com.example.common.vo.PlanetVO;

import java.util.List;

public interface PlanetService {
    /**
     * 创建星球
     */
    PlanetVO createPlanet(PlanetCreateDTO planetDTO, Long creatorId);

    /**
     * 获取星球列表
     */
    List<PlanetVO> getPlanetList();

    /**
     * 获取星球详情
     */
    PlanetVO getPlanetDetail(Long planetId);

    /**
     * 检查用户是否有权限访问星球
     */
    boolean checkUserPlanetAccess(Long userId, Long planetId);
}