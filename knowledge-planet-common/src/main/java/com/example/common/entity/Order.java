package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("orders") // Changed table name to plural 'orders'
public class Order {
    @TableId(type = IdType.ASSIGN_ID) // Use Snowflake ID generated by MybatisPlus or manually
    private Long id; // Changed type to Long for Snowflake
    private String orderId; // Business order ID (e.g., UUID or time-based) - Make this unique
    private Long userId;
    private Long articleId;
    private BigDecimal amount;
    private Integer status; // 0-待支付, 1-已支付, 2-已取消, 3-支付失败
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime paymentTime; // Time of successful payment
}