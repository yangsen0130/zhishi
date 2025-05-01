

// src/main/java/com/example/auth/controller/UserController.java
package com.example.auth.controller;

import com.example.auth.service.UserService;
import com.example.common.response.Response;
import com.example.common.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "处理用户信息相关请求")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info/{userId}")
    @Operation(summary = "获取用户信息")
    public Response<UserVO> getUserInfo(@PathVariable Long userId) {
        UserVO userVO = userService.getUserInfo(userId);
        return Response.success(userVO);
    }

    @GetMapping("/info")
    @Operation(summary = "获取当前用户信息")
    public Response<UserVO> getCurrentUserInfo(@RequestHeader("X-User-Id") Long userId) {
        UserVO userVO = userService.getUserInfo(userId);
        return Response.success(userVO);
    }
}