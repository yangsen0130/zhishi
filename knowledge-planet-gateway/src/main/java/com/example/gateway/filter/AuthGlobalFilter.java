package com.example.gateway.filter;

import com.example.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Value("#{'${whitelist.urls}'.split(',')}")
    private List<String> whitelist;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 1. 预检请求直接放行
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            return chain.filter(exchange);
        }

        // 2. 白名单路径放行
        if (isWhitelist(path)) {
            return chain.filter(exchange);
        }

        // 获取token
        String token = request.getHeaders().getFirst("Authorization");

        // 验证token
        if (token == null || token.isEmpty() || JwtUtil.parseToken(token) == null) {
            return unauthorizedResponse(exchange);
        }

        // 解析出用户信息
        Long userId = JwtUtil.getUserId(token);
        String username = JwtUtil.getUsername(token);

        // 将用户ID和用户名传递到下游服务
        ServerHttpRequest mutableReq = request.mutate()
                .header("X-User-Id", String.valueOf(userId))
                .header("X-User-Name", username)
                .build();

        return chain.filter(exchange.mutate().request(mutableReq).build());
    }

    private boolean isWhitelist(String path) {
        return whitelist.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String body = "{\"code\":401,\"message\":\"Unauthorized\"}";
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}