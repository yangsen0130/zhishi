package com.example.order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 支付服务接口
 */
public interface PaymentService {

    /**
     * 创建支付宝支付表单
     *
     * @param orderId 订单ID
     * @param subject 订单标题
     * @param totalAmount 支付金额
     * @param response HTTP响应对象
     * @throws Exception 支付过程中的异常
     */
    void createAlipayForm(String orderId, String subject, BigDecimal totalAmount, HttpServletResponse response) throws Exception;

    /**
     * 处理支付宝同步回调
     *
     * @param request HTTP请求对象
     * @return 处理结果（包含订单号和支付状态信息）
     */
    Map<String, Object> handleAlipayReturn(HttpServletRequest request);

    /**
     * 处理支付宝异步通知
     *
     * @param requestParams 回调参数
     * @return 处理结果，成功返回"success"，失败返回"failure"
     */
    String handleAlipayNotify(Map<String, String> requestParams);
} 