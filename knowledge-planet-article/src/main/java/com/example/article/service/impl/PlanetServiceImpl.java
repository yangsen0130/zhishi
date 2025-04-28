
// src/main/java/com/example/article/service/impl/PlanetServiceImpl.java
package com.example.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.article.mapper.PlanetMapper;
import com.example.article.mapper.UserPlanetMapper;
import com.example.article.service.PlanetService;
import com.example.common.dto.PlanetCreateDTO;
import com.example.common.entity.Planet;
import com.example.common.entity.UserPlanet;
import com.example.common.vo.PlanetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl extends ServiceImpl<PlanetMapper, Planet> implements PlanetService {

    @Autowired
    private UserPlanetMapper userPlanetMapper;

    @Override
    public PlanetVO createPlanet(PlanetCreateDTO planetDTO, Long creatorId) {
        Planet planet = new Planet();
        BeanUtil.copyProperties(planetDTO, planet);

        planet.setCreatorId(creatorId);
        planet.setStatus(1);

        // 保存星球
        this.save(planet);

        // 创建者自动加入星球
        UserPlanet userPlanet = new UserPlanet();
        userPlanet.setUserId(creatorId);
        userPlanet.setPlanetId(planet.getId());
        // 创建者的星球永不过期
        userPlanet.setExpireTime(LocalDateTime.now().plusYears(100));
        userPlanetMapper.insert(userPlanet);

        // 返回星球信息
        PlanetVO planetVO = new PlanetVO();
        BeanUtil.copyProperties(planet, planetVO);
        return planetVO;
    }

    @Override
    public List<PlanetVO> getPlanetList() {
        List<Planet> planets = this.list(new LambdaQueryWrapper<Planet>()
                .eq(Planet::getStatus, 1));

        return planets.stream().map(planet -> {
            PlanetVO planetVO = new PlanetVO();
            BeanUtil.copyProperties(planet, planetVO);
            return planetVO;
        }).collect(Collectors.toList());
    }

    @Override
    public PlanetVO getPlanetDetail(Long planetId) {
        Planet planet = this.getById(planetId);
        if (planet == null || planet.getStatus() != 1) {
            throw new RuntimeException("星球不存在或已关闭");
        }

        PlanetVO planetVO = new PlanetVO();
        BeanUtil.copyProperties(planet, planetVO);
        return planetVO;
    }

    @Override
    public boolean checkUserPlanetAccess(Long userId, Long planetId) {
        // 检查用户是否是创建者
        Planet planet = this.getById(planetId);
        if (planet != null && planet.getCreatorId().equals(userId)) {
            return true;
        }

        // 检查用户是否加入了星球且未过期
        UserPlanet userPlanet = userPlanetMapper.selectOne(new LambdaQueryWrapper<UserPlanet>()
                .eq(UserPlanet::getUserId, userId)
                .eq(UserPlanet::getPlanetId, planetId));

        if (userPlanet == null) {
            return false;
        }

        // 检查是否已过期
        return userPlanet.getExpireTime() == null || userPlanet.getExpireTime().isAfter(LocalDateTime.now());
    }
}
