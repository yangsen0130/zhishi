
// src/main/java/com/example/common/vo/OrderVO.java
package com.example.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVO {
    private String id;
    private Long userId;
    private Long planetId;
    private String planetName;
    private BigDecimal amount;
    private Integer status;
    private LocalDateTime paymentTime;
    private LocalDateTime createTime;
}