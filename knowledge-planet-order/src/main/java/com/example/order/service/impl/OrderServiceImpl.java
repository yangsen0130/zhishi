
// src/main/java/com/example/order/service/impl/OrderServiceImpl.java
package com.example.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.dto.OrderCreateDTO;
import com.example.common.entity.Order;
import com.example.common.entity.UserPlanet;
import com.example.common.feign.ArticleFeignClient;
import com.example.common.result.Result;
import com.example.common.vo.OrderVO;
import com.example.common.vo.PlanetVO;
import com.example.order.mapper.OrderMapper;
import com.example.order.mapper.UserPlanetMapper;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserPlanetMapper userPlanetMapper;

    @Autowired
    private ArticleFeignClient articleFeignClient;

    @Override
    public OrderVO createOrder(OrderCreateDTO orderDTO, Long userId) {
        // 获取星球信息
        Result<PlanetVO> planetResult = articleFeignClient.getPlanetDetail(orderDTO.getPlanetId());
        if (planetResult.getCode() != 200 || planetResult.getData() == null) {
            throw new RuntimeException("星球不存在");
        }

        PlanetVO planet = planetResult.getData();

        // 检查用户是否已经购买过该星球
        if (checkUserPlanetOrder(userId, planet.getId())) {
            throw new RuntimeException("您已经是该星球成员，无需重复购买");
        }

        // 创建订单
        Order order = new Order();
        order.setId(IdUtil.simpleUUID());
        order.setUserId(userId);
        order.setPlanetId(planet.getId());
        order.setAmount(planet.getPrice());
        order.setStatus(0); // 待支付

        // 保存订单
        this.save(order);

        // 返回订单信息
        OrderVO orderVO = new OrderVO();
        BeanUtil.copyProperties(order, orderVO);
        orderVO.setPlanetName(planet.getName());

        return orderVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO payOrder(String orderId, Long userId) {
        // 查询订单
        Order order = this.getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 检查订单是否属于当前用户
        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此订单");
        }

        // 检查订单状态
        if (order.getStatus() != 0) {
            throw new RuntimeException("订单状态不正确，无法支付");
        }

        // 模拟支付过程，实际项目中应对接支付服务
        order.setStatus(1); // 已支付
        order.setPaymentTime(LocalDateTime.now());

        // 更新订单状态
        this.updateById(order);

        // 创建用户星球关系，默认有效期1年
        UserPlanet userPlanet = new UserPlanet();
        userPlanet.setUserId(userId);
        userPlanet.setPlanetId(order.getPlanetId());
        userPlanet.setExpireTime(LocalDateTime.now().plusYears(1));
        userPlanetMapper.insert(userPlanet);

        // 返回订单信息
        OrderVO orderVO = new OrderVO();
        BeanUtil.copyProperties(order, orderVO);

        // 获取星球信息
        Result<PlanetVO> planetResult = articleFeignClient.getPlanetDetail(order.getPlanetId());
        if (planetResult.getCode() == 200 && planetResult.getData() != null) {
            orderVO.setPlanetName(planetResult.getData().getName());
        }

        return orderVO;
    }

    @Override
    public OrderVO getOrderDetail(String orderId, Long userId) {
        // 查询订单
        Order order = this.getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 检查订单是否属于当前用户
        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("无权查看此订单");
        }

        // 返回订单信息
        OrderVO orderVO = new OrderVO();
        BeanUtil.copyProperties(order, orderVO);

        // 获取星球信息
        Result<PlanetVO> planetResult = articleFeignClient.getPlanetDetail(order.getPlanetId());
        if (planetResult.getCode() == 200 && planetResult.getData() != null) {
            orderVO.setPlanetName(planetResult.getData().getName());
        }

        return orderVO;
    }

    @Override
    public List<OrderVO> getUserOrders(Long userId) {
        // 查询用户的所有订单
        List<Order> orders = this.list(new LambdaQueryWrapper<Order>()
                .eq(Order::getUserId, userId)
                .orderByDesc(Order::getCreateTime));

        if (orders.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取所有星球ID
        List<Long> planetIds = orders.stream()
                .map(Order::getPlanetId)
                .distinct()
                .collect(Collectors.toList());

        // 获取星球信息
        java.util.Map<Long, String> planetNames = new java.util.HashMap<>();
        for (Long planetId : planetIds) {
            Result<PlanetVO> planetResult = articleFeignClient.getPlanetDetail(planetId);
            if (planetResult.getCode() == 200 && planetResult.getData() != null) {
                planetNames.put(planetId, planetResult.getData().getName());
            }
        }

        // 转换为VO
        return orders.stream().map(order -> {
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order, orderVO);
            orderVO.setPlanetName(planetNames.getOrDefault(order.getPlanetId(), "未知星球"));
            return orderVO;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean checkUserPlanetOrder(Long userId, Long planetId) {
        // 查询用户是否有已支付的订单
        long count = this.count(new LambdaQueryWrapper<Order>()
                .eq(Order::getUserId, userId)
                .eq(Order::getPlanetId, planetId)
                .eq(Order::getStatus, 1));

        if (count > 0) {
            return true;
        }

        // 查询用户星球关系
        UserPlanet userPlanet = userPlanetMapper.selectOne(new LambdaQueryWrapper<UserPlanet>()
                .eq(UserPlanet::getUserId, userId)
                .eq(UserPlanet::getPlanetId, planetId));

        // 检查是否有记录且未过期
        return userPlanet != null &&
                (userPlanet.getExpireTime() == null ||
                        userPlanet.getExpireTime().isAfter(LocalDateTime.now()));
    }
}