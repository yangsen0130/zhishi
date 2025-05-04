// src/main/java/com/example/common/vo/UserVO.java
package com.example.common.vo;

import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer; // 导入 ToStringSerializer

@Data
public class UserVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
}