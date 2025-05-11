package com.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("message_outbox") // ShardingSphere routes this
public class MessageOutbox {
    @TableId(type = IdType.AUTO) // Simple auto-increment ID for the outbox table itself
    private Long id;
    private String messageId; // Unique ID for the message (e.g., UUID)
    private String aggregateType; // e.g., "Order"
    // *** CHANGED: Type to Long to match Order.id ***
    private Long aggregateId; // ID of the aggregate root (order.id, Sharding Key)
    private String eventType; // e.g., "PaymentSuccessEvent"
    private String payload; // JSON representation of the event DTO
    private String destination; // Target MQ topic (e.g., "payment-success-topic")
    private Integer status; // 0-Pending, 1-Sent, 2-Failed
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer retryCount; // Optional: track retry attempts
}