package com.example.order.controller;

import com.example.common.response.Response;
import com.example.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "订单接口", description = "创建和管理订单")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建文章购买订单")
    public Response<Map<String, String>> createOrder(
            @Parameter(description = "文章ID", required = true) @RequestParam Long articleId,
            @Parameter(description = "支付金额", required = true) @RequestParam BigDecimal amount,
            @Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId) { // Get userId from header injected by gateway

        log.info("Received request to create order for articleId: {}, userId: {}, amount: {}", articleId, userId, amount);
        String orderId = orderService.createOrder(userId, articleId, amount);

        Map<String, String> result = new HashMap<>();
        result.put("orderId", orderId);
        // In a real scenario, you'd likely return payment details or redirect to a payment gateway
        result.put("message", "订单创建成功，请继续支付");

        return Response.success(result);
    }

    @PostMapping("/pay/success/{orderId}")
    @Operation(summary = "模拟支付成功回调")
    public Response<Void> simulatePaymentSuccess(
            @Parameter(description = "业务订单ID", required = true) @PathVariable String orderId) {
        log.info("Simulating successful payment for orderId: {}", orderId);
        try {
            boolean success = orderService.processPaymentSuccess(orderId);
            if (success) {
                return Response.success();
            } else {
                // This case might mean it was already processed or another issue occurred
                log.warn("Payment processing for order {} did not result in a state change, possibly already processed.", orderId);
                // Still return success to the caller as the desired end state (paid) might be achieved
                return Response.success();
            }
        } catch (Exception e) {
            log.error("Error simulating payment success for orderId: {}", orderId, e);
            // Convert specific BusinessExceptions or return a generic error
            return Response.error(500, "处理支付成功回调时出错: " + e.getMessage());
        }
    }

    @PostMapping("/pay/fail/{orderId}")
    @Operation(summary = "模拟支付失败回调")
    public Response<Void> simulatePaymentFailure(
            @Parameter(description = "业务订单ID", required = true) @PathVariable String orderId) {
        log.info("Simulating failed payment for orderId: {}", orderId);
        try {
            orderService.processPaymentFailure(orderId);
            return Response.success();
        } catch (Exception e) {
            log.error("Error simulating payment failure for orderId: {}", orderId, e);
            return Response.error(500, "处理支付失败回调时出错: " + e.getMessage());
        }
    }

    // Add other endpoints as needed (e.g., get order details, list orders)
}