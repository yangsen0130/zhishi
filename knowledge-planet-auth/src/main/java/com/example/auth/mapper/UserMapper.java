// src/main/java/com/example/auth/mapper/UserMapper.java
package com.example.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT COUNT(*) FROM user WHERE username = #{username} AND status != 0") // Assuming 0 is deleted
    Long selectCountByUsername(@Param("username") String username);

    @Select("SELECT COUNT(*) FROM user WHERE email = #{email} AND status != 0") // Assuming 0 is deleted
    Long selectCountByEmail(@Param("email") String email);

    @Select("SELECT * FROM user WHERE username = #{username} AND status != 0") // Assuming 0 is deleted
    User selectByUsername(@Param("username") String username);
}