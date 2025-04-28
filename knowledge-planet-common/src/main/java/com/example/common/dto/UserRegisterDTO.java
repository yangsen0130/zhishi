// src/main/java/com/example/common/dto/UserRegisterDTO.java
package com.example.common.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
}
