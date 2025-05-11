package com.example.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSuccessEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;      // 订单ID (用于追踪和幂等性)
    private Long userId;         // 用户ID
    private Long articleId;      // 文章ID
    private BigDecimal amountPaid;   // 支付金额 (可选)
    private long timestamp;      // 事件发生时间戳

    @Override
    public String toString() {
        return "PaymentSuccessEvent{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", amountPaid=" + amountPaid +
                ", timestamp=" + timestamp +
                '}';
    }
}