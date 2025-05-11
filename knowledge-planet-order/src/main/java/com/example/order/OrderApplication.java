package com.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling; // Enable Scheduling for the task

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.order.mapper")
@EnableScheduling // Enable the scheduled task execution
@ComponentScan(basePackages = {"com.example.order", "com.example.common"}) // Scan common package for shared components like exception handlers
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}