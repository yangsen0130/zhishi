-- 创建数据库
CREATE DATABASE IF NOT EXISTS user_db_0;
CREATE DATABASE IF NOT EXISTS user_db_1;
CREATE DATABASE IF NOT EXISTS order_db_0;
CREATE DATABASE IF NOT EXISTS order_db_1;
CREATE DATABASE IF NOT EXISTS article_db_0;
CREATE DATABASE IF NOT EXISTS article_db_1;

-- 用户数据库0表结构
USE user_db_0;

-- 用户表0-3
CREATE TABLE `user_0` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表0';

CREATE TABLE `user_1` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表1';

CREATE TABLE `user_2` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表2';

CREATE TABLE `user_3` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表3';

-- 用户数据库1表结构
USE user_db_1;

CREATE TABLE `user_0` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表0';

CREATE TABLE `user_1` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表1';

CREATE TABLE `user_2` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表2';

CREATE TABLE `user_3` (
                          `id` bigint(20) NOT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表3';

-- 订单数据库0表结构
USE order_db_0;

CREATE TABLE `orders_0` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表0';

CREATE TABLE `orders_1` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表1';

CREATE TABLE `orders_2` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表2';

CREATE TABLE `orders_3` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表3';

CREATE TABLE `user_planet_0` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表0';

CREATE TABLE `user_planet_1` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表1';

CREATE TABLE `user_planet_2` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表2';

CREATE TABLE `user_planet_3` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表3';

-- 订单数据库1表结构
USE order_db_1;

CREATE TABLE `orders_0` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表0';

CREATE TABLE `orders_1` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表1';

CREATE TABLE `orders_2` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表2';

CREATE TABLE `orders_3` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表3';

CREATE TABLE `user_planet_0` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表0';

CREATE TABLE `user_planet_1` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表1';

CREATE TABLE `user_planet_2` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表2';

CREATE TABLE `user_planet_3` (
                                 `id` bigint(20) NOT NULL COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `planet_id` bigint(20) NOT NULL COMMENT '星球ID',
                                 `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `idx_user_planet` (`user_id`,`planet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户星球关系表3';

-- 文章数据库0表结构
USE article_db_0;

CREATE TABLE `planet_0` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表0';

CREATE TABLE `planet_1` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表1';

CREATE TABLE `planet_2` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表2';

CREATE TABLE `planet_3` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表3';

CREATE TABLE `article_0` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表0';

CREATE TABLE `article_1` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表1';

CREATE TABLE `article_2` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表2';

CREATE TABLE `article_3` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表3';

-- 文章数据库1表结构（续）
USE article_db_1;

CREATE TABLE `planet_0` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表0';

CREATE TABLE `planet_1` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表1';

CREATE TABLE `planet_2` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表2';

CREATE TABLE `planet_3` (
                            `id` bigint(20) NOT NULL COMMENT '星球ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='星球表3';

CREATE TABLE `article_0` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表0';

CREATE TABLE `article_1` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表1';

CREATE TABLE `article_2` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表2';

CREATE TABLE `article_3` (
                             `id` bigint(20) NOT NULL COMMENT '文章ID',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表3';


-- Drop Planet and UserPlanet related tables from both databases

USE article_db_0;
DROP TABLE IF EXISTS `planet_0`, `planet_1`, `planet_2`, `planet_3`;
-- Assuming user_planet might exist sharded or not, drop it if it exists.
-- If UserPlanet was in a different DB (like auth), drop it there.
-- DROP TABLE IF EXISTS `user_planet`; -- Adjust DB if necessary

USE article_db_1;
DROP TABLE IF EXISTS `planet_0`, `planet_1`, `planet_2`, `planet_3`;
-- DROP TABLE IF EXISTS `user_planet`; -- Adjust DB if necessary


-- Alter Article tables in both databases to remove planet_id

USE article_db_0;
ALTER TABLE `article_0` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_1` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_2` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_3` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;

USE article_db_1;
ALTER TABLE `article_0` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_1` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_2` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;
ALTER TABLE `article_3` DROP COLUMN `planet_id`, DROP INDEX `idx_planet`;

-- NOTE: You will also need to alter the `orders` table in its respective database
--       to remove the `planet_id` column. Example (adjust table name if needed):
-- ALTER TABLE `orders` DROP COLUMN `planet_id`;