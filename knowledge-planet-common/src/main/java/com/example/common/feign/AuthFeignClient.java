// src/main/java/com/example/common/feign/AuthFeignClient.java
package com.example.common.feign;

import com.example.common.response.Response;
import com.example.common.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "knowledge-planet-auth")
public interface AuthFeignClient {
    @GetMapping("/user/info/{userId}")
    Response<UserVO> getUserInfo(@PathVariable("userId") Long userId);

    @PostMapping("/auth/validate")
    Response<UserVO> validateToken(@RequestHeader("Authorization") String token);
}
