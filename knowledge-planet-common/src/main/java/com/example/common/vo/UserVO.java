// src/main/java/com/example/common/vo/UserVO.java
package com.example.common.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
}