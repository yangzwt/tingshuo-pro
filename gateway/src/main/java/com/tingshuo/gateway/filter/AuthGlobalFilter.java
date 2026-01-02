package com.tingshuo.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingshuo.gateway.config.JwtProperties;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName com.tingshuo.gateway.filter
 *
 * @author tingshuo
 * @version JDK 8
 * @className AuthGlobalFilter
 * @date 2026/1/2 17:42
 * @description 类描述信息 鉴权全局过滤器
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtProperties jwtProperties;

    // 白名单（支持 Ant 风格）
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/auth/login",
            "/auth/logout",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println("【Gateway】JwtProperties.secret = " + jwtProperties.getSecret());
        System.out.println("【Gateway】JwtProperties.expiration = " + jwtProperties.getExpiration());
        // 1. 白名单直接放行
        if (isWhiteList(path)) {
            return chain.filter(exchange);
        }

        // 2. 获取 Authorization 头
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange.getResponse(), "缺少有效令牌");
        }

        String token = authHeader.substring(7); // 去掉 "Bearer "

        // 3. 初始化 JwtParser
        JwtParser jwtParser = new JwtParser(jwtProperties.getSecret());

        // 4. 验证 token
        if (!jwtParser.validateToken(token)) {
            return unauthorized(exchange.getResponse(), "无效令牌");
        }

        // 5. 解析用户信息并透传
        try {
            Claims claims = jwtParser.parseToken(token);
            String userId = String.valueOf(claims.get("userId", Long.class));
            String username = claims.getSubject();

            // 构建新请求头（透传给下游）
            ServerHttpRequest newRequest = request.mutate()
                    .header("X-User-Id", userId)
                    .header("X-Username", username)
                    .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        } catch (Exception e) {
            return unauthorized(exchange.getResponse(), "令牌解析失败");
        }
    }

    private boolean isWhiteList(String path) {
        return WHITE_LIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private Mono<Void> unauthorized(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> result = new HashMap<>();
        result.put("code",401);
        result.put("msg",message);

        try {
            byte[] bytes = new ObjectMapper().writeValueAsBytes(result);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @Override
    public int getOrder() {
        return -100; // 优先级高
    }

}
