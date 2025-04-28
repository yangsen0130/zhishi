// src/main/java/com/example/article/mapper/PlanetMapper.java
package com.example.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.Planet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanetMapper extends BaseMapper<Planet> {
}