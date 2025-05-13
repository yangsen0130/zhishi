package com.example.order.controller;

import com.example.common.entity.Order;
import com.example.common.response.Response;
import com.example.order.service.OrderService;
import com.example.order.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private PaymentService paymentService;

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
        result.put("message", "订单创建成功，请继续支付");
        result.put("payUrl", "/order/pay?orderId=" + orderId); // 添加支付链接

        return Response.success(result);
    }

    @GetMapping("/pay")
    @Operation(summary = "跳转到支付宝支付")
    public void payWithAlipay(
            @Parameter(description = "订单ID", required = true) @RequestParam String orderId,
            HttpServletResponse response) {
        try {
            // 从数据库获取订单详情
            Order order = orderService.getOrderByOrderId(orderId);

            // 构建支付标题
            String subject = "知识星球文章-" + order.getArticleId() + "购买";

            // 调用支付宝支付
            paymentService.createAlipayForm(orderId, subject, order.getAmount(), response);
        } catch (Exception e) {
            log.error("跳转到支付宝支付失败：", e);
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("跳转到支付宝支付失败：" + e.getMessage());
            } catch (Exception ex) {
                log.error("返回错误信息失败", ex);
            }
        }
    }

    @GetMapping("/alipay/return")
    @Operation(summary = "支付宝同步回调")
    public String alipayReturn(HttpServletRequest request) {
        log.info("接收到支付宝同步回调");
        Map<String, Object> result = paymentService.handleAlipayReturn(request);

        // 构建简单的HTML响应，显示支付结果
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html>")
                    .append("<html><head><meta charset=\"utf-8\"><title>支付结果</title></head>")
                    .append("<body style=\"text-align:center;padding:50px;\">")
                    .append("<h1>").append(result.get("message")).append("</h1>");

        if (result.containsKey("orderId")) {
            htmlResponse.append("<p>订单号: ").append(result.get("orderId")).append("</p>");
        }
        if (result.containsKey("tradeNo")) {
            htmlResponse.append("<p>支付宝交易号: ").append(result.get("tradeNo")).append("</p>");
        }
        if (result.containsKey("totalAmount")) {
            htmlResponse.append("<p>支付金额: ").append(result.get("totalAmount")).append("</p>");
        }

        htmlResponse.append("<a href=\"/\">返回首页</a>")
                    .append("</body></html>");

        return htmlResponse.toString();
    }

    @PostMapping("/alipay/notify")
    @Operation(summary = "支付宝异步通知")
    @ResponseBody
    public String alipayNotify(HttpServletRequest request) {
        log.info("接收到支付宝异步通知");

        // 将请求参数转换为Map
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        // 处理异步通知
        return paymentService.handleAlipayNotify(params);
    }


    /**
     * 模拟支付成功回调 - 仅限开发/测试环境使用。
     * 这个接口会直接触发支付成功后的业务逻辑，包括更新订单状态和发送消息到MQ。
     *
     * @param orderId 业务订单ID
     * @return Response
     */
    @PostMapping("/dev/simulate-payment-success/{orderId}")
    @Operation(summary = "模拟支付成功回调 (仅限开发/测试环境)",
            description = "此接口用于在开发或测试环境中模拟支付成功场景，触发后续业务流程。请勿在生产环境暴露或使用。")
    public Response<String> simulatePaymentSuccess(
            @Parameter(description = "业务订单ID", required = true) @PathVariable String orderId) {

        try {
            boolean success = orderService.processPaymentSuccess(orderId);
            if (success) {
                log.info("Payment success simulation processed for orderId: {}. Order status updated and message queued.", orderId);
                return Response.success("模拟支付成功处理完成，订单 " + orderId + " 已标记为支付成功，相关消息已进入队列。");
            } else {
                log.warn("Payment success simulation for order {} did not result in a state change, possibly already processed or an issue occurred.", orderId);
                return Response.success("模拟支付成功请求已收到，但订单 " + orderId + " 状态未发生改变（可能已处理或存在问题）。");
            }
        } catch (Exception e) {
            log.error("Error simulating payment success for orderId: {}. Error: {}", orderId, e.getMessage(), e);
            // 将具体的业务异常或系统异常信息返回给调用者
            return Response.error(500, "模拟支付成功处理时出错: " + e.getMessage());
        }
    }


    @PostMapping("/pay/fail/{orderId}")
    @Operation(summary = "模拟支付失败回调（不推荐使用，使用支付宝支付）")
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