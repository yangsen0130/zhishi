// src/main/java/com/example/common/dto/PlanetCreateDTO.java
package com.example.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlanetCreateDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String cover;
}
