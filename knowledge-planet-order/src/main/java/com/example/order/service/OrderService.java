package com.example.order.service;

import com.example.common.dto.PaymentSuccessEvent;
import com.example.common.entity.Order;

import java.math.BigDecimal;

public interface OrderService {

    /**
     * Creates a new order (simulates the initial step).
     *
     * @param userId    The ID of the user placing the order.
     * @param articleId The ID of the article being ordered.
     * @param amount    The amount for the order.
     * @return The generated business order ID.
     */
    String createOrder(Long userId, Long articleId, BigDecimal amount);

    /**
     * Processes a successful payment for a given order.
     * This method implements the Transactional Outbox pattern:
     * 1. Updates the order status to 'PAID'.
     * 2. Saves the PaymentSuccessEvent details to the message_outbox table.
     * Both operations occur within the same local database transaction.
     *
     * @param orderId The business ID of the order that was paid.
     * @return true if processing was successful, false otherwise.
     */
    boolean processPaymentSuccess(String orderId);

    /**
     * Simulates processing a failed payment.
     *
     * @param orderId The business ID of the order.
     */
    void processPaymentFailure(String orderId);
    
    /**
     * 根据业务订单ID获取订单详情
     *
     * @param orderId 业务订单ID
     * @return 订单实体
     */
    Order getOrderByOrderId(String orderId);
}