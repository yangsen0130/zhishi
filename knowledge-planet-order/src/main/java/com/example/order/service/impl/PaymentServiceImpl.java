package com.example.order.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.common.exception.BusinessException;
import com.example.common.response.Code;
import com.example.order.config.AlipayConfig;
import com.example.order.service.OrderService;
import com.example.order.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private OrderService orderService;

    @Override
    public void createAlipayForm(String orderId, String subject, BigDecimal totalAmount, HttpServletResponse response) throws Exception {
        // 创建API请求对象
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(alipayConfig.getReturnUrl());
        request.setNotifyUrl(alipayConfig.getNotifyUrl());

        // 构建业务参数
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderId); // 订单号
        bizContent.put("total_amount", totalAmount.toString()); // 支付金额
        bizContent.put("subject", subject); // 订单标题
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY"); // 产品码，固定值

        request.setBizContent(bizContent.toString());
        log.info("支付宝支付请求参数: {}", bizContent.toString());

        // 调用支付宝接口，生成支付表单
        String formHtml;
        try {
            formHtml = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            log.error("生成支付宝支付表单失败: ", e);
            response.setContentType("text/html;charset=" + alipayConfig.getCharset());
            PrintWriter writer = response.getWriter();
            writer.write("<h1>生成支付宝支付表单失败</h1><p>" + e.getMessage() + "</p>");
            writer.flush();
            writer.close();
            throw new BusinessException(Code.SYSTEM_ERROR, "生成支付宝支付表单失败");
        }

        // 输出表单HTML，浏览器会自动跳转到支付宝支付页面
        response.setContentType("text/html;charset=" + alipayConfig.getCharset());
        PrintWriter writer = response.getWriter();
        writer.write(formHtml);
        writer.flush();
        writer.close();
    }

    @Override
    public Map<String, Object> handleAlipayReturn(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        
        // 将请求参数转换为Map
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        log.debug("支付宝同步回调参数: {}", params);

        try {
            // 验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), 
                                                             alipayConfig.getCharset(), alipayConfig.getSignType());
            
            if (signVerified) {
                log.info("支付宝同步回调验签成功");
                // 验签成功，获取订单信息
                String outTradeNo = params.get("out_trade_no"); // 商户订单号
                String tradeNo = params.get("trade_no"); // 支付宝交易号
                String totalAmount = params.get("total_amount"); // 订单金额
                
                result.put("success", true);
                result.put("message", "支付成功（同步通知）");
                result.put("orderId", outTradeNo);
                result.put("tradeNo", tradeNo);
                result.put("totalAmount", totalAmount);
                
                // 同步回调仅用于页面展示，实际业务逻辑在异步通知中处理
            } else {
                log.warn("支付宝同步回调验签失败");
                result.put("success", false);
                result.put("message", "支付验签失败（同步通知）");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝同步回调验签异常: ", e);
            result.put("success", false);
            result.put("message", "支付验签异常: " + e.getMessage());
        }
        
        return result;
    }

    @Override
    public String handleAlipayNotify(Map<String, String> params) {
        log.info("接收到支付宝异步通知: {}", params);
        
        try {
            // 验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(),
                                                             alipayConfig.getCharset(), alipayConfig.getSignType());
            
            if (signVerified) {
                log.info("支付宝异步通知验签成功");
                
                // 验证关键参数
                String outTradeNo = params.get("out_trade_no"); // 订单号
                String tradeStatus = params.get("trade_status"); // 交易状态
                String totalAmount = params.get("total_amount"); // 支付金额
                String appId = params.get("app_id"); // 应用ID
                
                // 验证应用ID是否一致
                if (!alipayConfig.getAppId().equals(appId)) {
                    log.warn("应用ID不匹配. 预期: {}, 收到: {}", alipayConfig.getAppId(), appId);
                    return "failure";
                }
                
                // 处理支付状态
                if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
                    log.info("订单支付成功. 订单号: {}, 支付宝交易状态: {}", outTradeNo, tradeStatus);
                    
                    // 更新订单状态为已支付，实现事务处理
                    boolean success = orderService.processPaymentSuccess(outTradeNo);
                    if (success) {
                        log.info("订单状态更新成功. 订单号: {}", outTradeNo);
                        return "success";
                    } else {
                        log.warn("订单状态更新失败. 订单号: {}", outTradeNo);
                        // 虽然更新失败，但可能是因为重复通知，还是返回success避免支付宝重复通知
                        return "success";
                    }
                } else {
                    log.warn("订单支付未成功，状态为: {}. 订单号: {}", tradeStatus, outTradeNo);
                    // 处理其他状态（如等待买家付款等）
                    // 对于非成功状态，一般也返回success表示已收到通知
                    return "success";
                }
            } else {
                log.warn("支付宝异步通知验签失败");
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("支付宝异步通知处理异常: ", e);
            return "failure";
        } catch (Exception e) {
            log.error("处理支付宝异步通知时发生错误: ", e);
            return "failure";
        }
    }
} 