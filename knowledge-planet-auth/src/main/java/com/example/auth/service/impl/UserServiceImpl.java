
// src/main/java/com/example/auth/service/impl/UserServiceImpl.java
package com.example.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.auth.mapper.UserMapper;
import com.example.auth.service.UserService;
import com.example.common.dto.UserLoginDTO;
import com.example.common.dto.UserRegisterDTO;
import com.example.common.entity.User;
import com.example.common.util.JwtUtil;
import com.example.common.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserVO register(UserRegisterDTO registerDTO) {
        // 检查用户名是否存在
        long count = this.count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, registerDTO.getUsername()));
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否存在
        if(registerDTO.getEmail() != null) {
            count = this.count(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, registerDTO.getEmail()));
            if (count > 0) {
                throw new RuntimeException("邮箱已存在");
            }
        }

        // 创建用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        // 密码加密存储
        user.setPassword(BCrypt.hashpw(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setStatus(1); // 正常状态

        // 保存用户
        this.save(user);

        // 返回用户信息
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public String login(UserLoginDTO loginDTO) {
        // 查询用户
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, loginDTO.getUsername()));

        // 用户不存在或已禁用
        if (user == null || user.getStatus() != 1) {
            throw new RuntimeException("用户不存在或已被禁用");
        }

        // 密码校验
        if (!BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 生成token
        return JwtUtil.generateToken(user.getId(), user.getUsername());
    }

    @Override
    public UserVO getUserInfo(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserVO validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token不能为空");
        }

        // 解析token
        Long userId = JwtUtil.getUserId(token);
        if (userId == null) {
            throw new RuntimeException("无效的Token");
        }

        // 查询用户信息
        return getUserInfo(userId);
    }
}