
// src/main/java/com/example/common/entity/UserPlanet.java
package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_planet")
public class UserPlanet {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long planetId;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}