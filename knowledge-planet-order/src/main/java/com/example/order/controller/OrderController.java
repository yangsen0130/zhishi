// src/main/java/com/example/order/controller/OrderController.java
package com.example.order.controller;

import com.example.common.dto.OrderCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.OrderVO;
import com.example.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "订单接口", description = "处理订单相关请求")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    public Response<OrderVO> createOrder(@RequestBody OrderCreateDTO orderDTO,
                                         @RequestHeader("X-User-Id") Long userId) {
        OrderVO orderVO = orderService.createOrder(orderDTO, userId);
        return Response.success(orderVO);
    }

    @PostMapping("/pay/{orderId}")
    @Operation(summary = "支付订单")
    public Response<OrderVO> payOrder(@PathVariable String orderId,
                                      @RequestHeader("X-User-Id") Long userId) {
        OrderVO orderVO = orderService.payOrder(orderId, userId);
        return Response.success(orderVO);
    }

    @GetMapping("/detail/{orderId}")
    @Operation(summary = "获取订单详情")
    public Response<OrderVO> getOrderDetail(@PathVariable String orderId,
                                            @RequestHeader("X-User-Id") Long userId) {
        OrderVO orderVO = orderService.getOrderDetail(orderId, userId);
        return Response.success(orderVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取用户订单列表")
    public Response<List<OrderVO>> getUserOrders(@RequestHeader("X-User-Id") Long userId) {
        List<OrderVO> orderList = orderService.getUserOrders(userId);
        return Response.success(orderList);
    }

    @GetMapping("/check/{userId}/{planetId}")
    @Operation(summary = "检查用户是否已购买星球")
    public Response<Boolean> checkUserPlanetOrder(@PathVariable Long userId,
                                                  @PathVariable Long planetId) {
        boolean hasPurchased = orderService.checkUserPlanetOrder(userId, planetId);
        return Response.success(hasPurchased);
    }
}