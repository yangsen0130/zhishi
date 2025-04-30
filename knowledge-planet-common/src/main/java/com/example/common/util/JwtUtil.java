// src/main/java/com/example/common/util/JwtUtil.java
package com.example.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {
    private static final String SECRET_KEY = "knowledge_planet_secret_key_must_be_long_enough_for_hs512_algorithm";
    private static final long EXPIRATION = 86400000; // 24小时

    // 创建signing key
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)  // 使用key对象而不是直接使用字符串
                .compact();
    }

    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()  // 使用parserBuilder()而不是parser()
                    .setSigningKey(key)  // 使用相同的key对象
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT token解析异常", e);
            return null;
        }
    }

    public static Long getUserId(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.get("userId", Long.class) : null;
    }

    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.get("username", String.class) : null;
    }

    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        return claims != null && claims.getExpiration().before(new Date());
    }
}