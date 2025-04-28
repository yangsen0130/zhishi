// src/main/java/com/example/order/service/OrderService.java
package com.example.order.service;

import com.example.common.dto.OrderCreateDTO;
import com.example.common.vo.OrderVO;

import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     */
    OrderVO createOrder(OrderCreateDTO orderDTO, Long userId);

    /**
     * 支付订单
     */
    OrderVO payOrder(String orderId, Long userId);

    /**
     * 获取订单详情
     */
    OrderVO getOrderDetail(String orderId, Long userId);

    /**
     * 获取用户订单列表
     */
    List<OrderVO> getUserOrders(Long userId);

    /**
     * 检查用户是否已购买星球
     */
    boolean checkUserPlanetOrder(Long userId, Long planetId);
}
