// src/main/java/com/example/auth/service/UserService.java
package com.example.auth.service;

import com.example.common.dto.UserLoginDTO;
import com.example.common.dto.UserRegisterDTO;
import com.example.common.entity.User;
import com.example.common.vo.UserVO;

public interface UserService {
    /**
     * 用户注册
     */
    UserVO register(UserRegisterDTO registerDTO);

    /**
     * 用户登录
     */
    String login(UserLoginDTO loginDTO);

    /**
     * 获取用户信息
     */
    UserVO getUserInfo(Long userId);

    /**
     * 校验token
     */
    UserVO validateToken(String token);
}
