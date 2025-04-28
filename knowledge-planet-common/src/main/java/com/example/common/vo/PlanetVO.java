// src/main/java/com/example/common/vo/PlanetVO.java
package com.example.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PlanetVO {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private BigDecimal price;
    private String cover;
    private LocalDateTime createTime;
}