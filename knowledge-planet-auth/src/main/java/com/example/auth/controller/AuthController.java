// src/main/java/com/example/auth/controller/AuthController.java
package com.example.auth.controller;

import com.example.auth.service.UserService;
import com.example.common.dto.UserLoginDTO;
import com.example.common.dto.UserRegisterDTO;
import com.example.common.result.Result;
import com.example.common.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证接口", description = "处理用户登录注册")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<UserVO> register(@RequestBody UserRegisterDTO registerDTO) {
        UserVO userVO = userService.register(registerDTO);
        return Result.success(userVO);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<Map<String, Object>> login(@RequestBody UserLoginDTO loginDTO) {
        String token = userService.login(loginDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.success(map);
    }

    @PostMapping("/validate")
    @Operation(summary = "验证Token")
    public Result<UserVO> validateToken(@RequestHeader("Authorization") String token) {
        UserVO userVO = userService.validateToken(token);
        return Result.success(userVO);
    }
}
