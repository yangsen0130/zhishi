
// src/main/java/com/example/common/entity/Planet.java
package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("planet")
public class Planet {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private BigDecimal price;
    private String cover;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}