package com.example.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    // Add this method
    @Select("SELECT * FROM orders WHERE order_id = #{orderId}")
    Order selectByBusinessOrderId(@Param("orderId") String orderId);
}