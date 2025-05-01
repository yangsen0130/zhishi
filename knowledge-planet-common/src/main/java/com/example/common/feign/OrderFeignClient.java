
// src/main/java/com/example/common/feign/OrderFeignClient.java
package com.example.common.feign;

import com.example.common.dto.OrderCreateDTO;
import com.example.common.response.Response;
import com.example.common.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "knowledge-planet-order")
public interface OrderFeignClient {
    @PostMapping("/order/create")
    Response<OrderVO> createOrder(@RequestBody OrderCreateDTO orderCreateDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/order/check/{userId}/{planetId}")
    Response<Boolean> checkUserPlanetOrder(@PathVariable("userId") Long userId, @PathVariable("planetId") Long planetId);
}