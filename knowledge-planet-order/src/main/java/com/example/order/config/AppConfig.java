package com.example.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Register JSR310 module for Java 8 date/time types (LocalDateTime etc.)
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}