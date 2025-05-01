-- 用户表
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` varchar(50) NOT NULL COMMENT '用户名',
                        `password` varchar(100) NOT NULL COMMENT '密码',
                        `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                        `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                        `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
                        `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-禁用',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `idx_username` (`username`),
                        UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 知识星球表
CREATE TABLE `planet` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '星球ID',
                          `name` varchar(100) NOT NULL COMMENT '星球名称',
                          `description` text COMMENT '星球描述',
                          `creator_id` bigint(20) NOT NULL COMMENT '创建者ID',
                          `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '加入价格',
                          `cover` varchar(200) DEFAULT NULL COMMENT '封面图片',
                          `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-关闭',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          KEY `idx_creator` (`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识星球表';

-- 文章表
CREATE TABLE `article` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
                           `title` varchar(200) NOT NULL COMMENT '文章标题',
                           `content` longtext NOT NULL COMMENT '文章内容',
                           `author_id` bigint(20) NOT NULL COMMENT '作者ID',
                           `planet_id` bigint(20) NOT NULL COMMENT '所属星球ID',
                           `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`id`),
                           KEY `idx_author` (`author_id`),
                           KEY `idx_planet` (`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 订单表
CREATE TABLE `orders` (
                          `id` varchar(64) NOT NULL COMMENT '订单ID',
                          `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                          `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                          `amount` decimal(10,2) NOT NULL COMMENT '支付金额',
                          `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0-待支付 1-已支付 2-已取消',
                          `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          KEY `idx_user` (`user_id`),
                          KEY `idx_planet` (`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 用户星球关系表
CREATE TABLE `user_planet` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                               `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                               `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表';