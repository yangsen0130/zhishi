
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
import com.example.common.exception.BusinessException;
import com.example.common.redis.RedisCache;
import com.example.common.redis.RedisKey;
import com.example.common.redis.RedisManager;
import com.example.common.response.Code;
import com.example.common.util.JwtUtil;
import com.example.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public UserVO register(UserRegisterDTO registerDTO) {
        // 检查用户名是否存在
        long count = this.count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, registerDTO.getUsername()));
        if (count > 0) {
            throw new BusinessException(Code.USERNAME_ALREADY_EXIST);
        }

        // 检查邮箱是否存在
        if(registerDTO.getEmail() != null) {
            count = this.count(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, registerDTO.getEmail()));
            if (count > 0) {
                throw new BusinessException(Code.EMAIL_ALREADY_EXIST);
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
        String username = loginDTO.getUsername();
        String loginErrorKey = RedisManager.createIfNotExist(RedisKey.LOGIN_USERNAME_INPUT_ERROR_CNT, username);

        // 检查用户是否因为多次失败尝试而被锁定
        Integer errorCount = redisCache.get(loginErrorKey, Integer.class);
        if (errorCount != null && errorCount >= 3) {
            throw new BusinessException(Code.USER_ACCOUNT_LOCKED, "登录失败次数过多，账户已锁定，请30分钟后再试");
        }

        // 查询用户
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginDTO.getUsername()));

        // 用户不存在或已禁用
        if (user == null || user.getStatus() != 1) {
            // 增加错误计数
            incrementLoginErrorCount(loginErrorKey);
            throw new BusinessException(Code.USER_NOT_EXIST);
        }

        // 密码校验
        if (!BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
            // 增加错误计数
            incrementLoginErrorCount(loginErrorKey);
            throw new BusinessException(Code.USER_PASSWORD_ERROR);
        }

        // 登录成功，清除错误计数
        redisCache.delete(loginErrorKey);

        // 生成token
        return JwtUtil.generateToken(user.getId(), user.getUsername());
    }

    /**
     * 增加登录错误计数并设置过期时间
     */
    private void incrementLoginErrorCount(String key) {
        Long count = redisCache.increment(key, 1);
        // 为第一次错误设置过期时间（30分钟后自动解锁）
        if (count != null && count == 1) {
            redisCache.expire(key, 30, TimeUnit.MINUTES);
        }
    }

    @Override
    public UserVO getUserInfo(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            throw new BusinessException(Code.USER_NOT_EXIST);
        }

        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public UserVO validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new BusinessException(Code.PARAM_ERROR, "Token不能为空");
        }

        // 解析token
        Long userId = JwtUtil.getUserId(token);
        if (userId == null) {
            throw new BusinessException(Code.UNAUTHORIZED, "无效的Token");
        }

        // 查询用户信息
        return getUserInfo(userId);
    }
}