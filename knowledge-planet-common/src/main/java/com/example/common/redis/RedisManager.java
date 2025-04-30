package com.example.common.redis;

public class RedisManager {
    /**
     * Create a Redis key with prefix and value
     */
    public static String createIfNotExist(String keyPrefix, String keyValue) {
        return keyPrefix + keyValue;
    }

    /**
     * Get Redis key with prefix and value
     */
    public static String getKey(String keyPrefix, String keyValue) {
        return keyPrefix + keyValue;
    }
}