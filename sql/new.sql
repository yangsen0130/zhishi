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


-- ======================================================================
-- Step 1: Clean up old Planet related structures in Article Databases
-- ======================================================================

-- Connect to the first article database
USE article_db_0;

-- Drop Planet tables if they exist
DROP TABLE IF EXISTS `planet_0`;
DROP TABLE IF EXISTS `planet_1`;
DROP TABLE IF EXISTS `planet_2`;
DROP TABLE IF EXISTS `planet_3`;

-- Alter Article tables to remove planet_id column and index
ALTER TABLE `article_0` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_1` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_2` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_3` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;

-- Connect to the second article database
USE article_db_1;

-- Drop Planet tables if they exist
DROP TABLE IF EXISTS `planet_0`;
DROP TABLE IF EXISTS `planet_1`;
DROP TABLE IF EXISTS `planet_2`;
DROP TABLE IF EXISTS `planet_3`;

-- Alter Article tables to remove planet_id column and index
ALTER TABLE `article_0` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_1` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_2` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;
ALTER TABLE `article_3` DROP COLUMN IF EXISTS `planet_id`, DROP INDEX IF EXISTS `idx_planet`;

-- ======================================================================
-- Step 2: Recreate Article tables (Cleaned) in Article Databases
-- ======================================================================

-- Connect to the first article database
USE article_db_0;

CREATE TABLE `article_0` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- Optional: Add is_paid column if needed for logic
    -- `is_paid` tinyint(1) DEFAULT 1 COMMENT '是否需要付费访问',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表0 (Cleaned)';

CREATE TABLE `article_1` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表1 (Cleaned)';

CREATE TABLE `article_2` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表2 (Cleaned)';

CREATE TABLE `article_3` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表3 (Cleaned)';

-- Connect to the second article database
USE article_db_1;

-- Repeat CREATE TABLE statements for article_0, article_1, article_2, article_3 here...
CREATE TABLE `article_0` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表0 (Cleaned)';

CREATE TABLE `article_1` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表1 (Cleaned)';

CREATE TABLE `article_2` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表2 (Cleaned)';

CREATE TABLE `article_3` (
                             `id` bigint NOT NULL COMMENT '文章ID (Sharding Key)',
                             `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
                             `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
                             `author_id` bigint NOT NULL COMMENT '作者ID',
                             `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `idx_author` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表3 (Cleaned)';


-- ======================================================================
-- Step 3: Create User Article Permission tables (Sharded) in Article Databases
-- ======================================================================

-- Connect to the first article database
USE article_db_0;

CREATE TABLE `user_article_permission_0` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`) COMMENT 'Ensures idempotency based on order',
    -- Optional: UNIQUE KEY `uk_user_article` (`user_id`, `article_id`), -- If user buys article only once
                                             KEY `idx_user_article` (`user_id`,`article_id`) COMMENT 'Index for checking user access'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 0';

CREATE TABLE `user_article_permission_1` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 1';

CREATE TABLE `user_article_permission_2` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 2';

CREATE TABLE `user_article_permission_3` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 3';


-- Connect to the second article database
USE article_db_1;

-- Repeat CREATE TABLE statements for user_article_permission_0, _1, _2, _3 here...
CREATE TABLE `user_article_permission_0` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 0';

CREATE TABLE `user_article_permission_1` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 1';

CREATE TABLE `user_article_permission_2` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 2';

CREATE TABLE `user_article_permission_3` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK',
                                             `user_id` bigint NOT NULL COMMENT 'User ID',
                                             `article_id` bigint NOT NULL COMMENT 'Article ID (Sharding Key)',
                                             `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID that granted permission',
                                             `grant_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time permission was granted',
                                             PRIMARY KEY (`id`),
                                             UNIQUE KEY `uk_order_id` (`order_id`),
                                             KEY `idx_user_article` (`user_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User Permissions for Articles Table 3';


-- ======================================================================
-- Step 4: Create Order and Message Outbox tables (Sharded) in NEW Order Databases
-- ======================================================================

-- Create the Order Databases first if they don't exist
CREATE DATABASE IF NOT EXISTS order_db_0 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS order_db_1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Connect to the first order database
USE order_db_0;

-- Create sharded orders tables
CREATE TABLE `orders_0` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 0';

CREATE TABLE `orders_1` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 1';

CREATE TABLE `orders_2` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 2';

CREATE TABLE `orders_3` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 3';

-- Create sharded message_outbox tables (sharded by aggregate_id -> order_id -> order.id)
CREATE TABLE `message_outbox_0` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`) COMMENT 'Index for querying pending messages',
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 0';

-- Repeat CREATE TABLE for message_outbox_1, _2, _3 here...
CREATE TABLE `message_outbox_1` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 1';

CREATE TABLE `message_outbox_2` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 2';

CREATE TABLE `message_outbox_3` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 3';


-- Connect to the second order database
USE order_db_1;

-- Repeat CREATE TABLE statements for orders_0, _1, _2, _3 here...
-- Repeat CREATE TABLE statements for message_outbox_0, _1, _2, _3 here...
CREATE TABLE `orders_0` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 0';
CREATE TABLE `orders_1` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 1';
CREATE TABLE `orders_2` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 2';
CREATE TABLE `orders_3` (
                            `id` bigint NOT NULL COMMENT 'Primary Key (e.g., Snowflake ID, Sharding Key)',
                            `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Business Order ID (Unique)',
                            `user_id` bigint NOT NULL COMMENT 'User ID',
                            `article_id` bigint NOT NULL COMMENT 'Article ID',
                            `amount` decimal(10,2) NOT NULL COMMENT 'Order Amount',
                            `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Paid, 2-Cancelled, 3-Failed',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                            `payment_time` datetime DEFAULT NULL COMMENT 'Payment Success Time',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_order_id` (`order_id`),
                            KEY `idx_user_id` (`user_id`),
                            KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Order Information Table 3';

CREATE TABLE `message_outbox_0` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 0';
CREATE TABLE `message_outbox_1` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 1';
CREATE TABLE `message_outbox_2` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 2';
CREATE TABLE `message_outbox_3` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Auto-increment PK for this table',
                                    `message_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique Message ID (e.g., UUID)',
                                    `aggregate_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of aggregate root (e.g., Order)',
                                    `aggregate_id` bigint NOT NULL COMMENT 'ID of the aggregate root (order.id, Sharding Key)',
                                    `event_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Type of event (e.g., PaymentSuccessEvent)',
                                    `payload` json NOT NULL COMMENT 'Event payload as JSON string',
                                    `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Target MQ topic/queue',
                                    `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-Pending, 1-Sent, 2-Failed',
                                    `retry_count` int DEFAULT '0' COMMENT 'Number of send attempts',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last Update Time',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_message_id` (`message_id`),
                                    KEY `idx_status_createtime` (`status`,`create_time`),
                                    KEY `idx_aggregate_id` (`aggregate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Transactional Outbox Table 3';