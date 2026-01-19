package com.tingshuo.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tingshuo.gateway.config.JwtProperties;
import com.tingshuo.gateway.config.RedisService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    @Autowired
    private JwtParser jwtParser;

    @Autowired
    private RedisService redisService;
    // 黑名单（JTI）前缀
    private static final String BLACKLIST_PREFIX = "jwt:blacklist:";
    // 【新增】路径与所需权限的映射（支持 AntPathMatcher）
    //private static final Map<String, String> PATH_PERMISSION_MAP = new HashMap<>();
    private final Map<String, String> PATH_PERMISSION_MAP = new HashMap<>();

    // 白名单（支持 Ant 风格）
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/api/auth/login",
            "/api/auth/logout",
            "/api/auth/refresh",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath(); // ← 关键：获取字符串路径
        // 1. 跳过白名单路径
        if (isExcludePath(path)) {
            return chain.filter(exchange);
        }

        // 2. 获取 token
        String authHeader = request.getHeaders().getFirst(jwtProperties.getHeader());
        //System.out.println("【Gateway】Received Authorization header: " + authHeader);
        if (authHeader == null || !authHeader.startsWith(jwtProperties.getTokenPrefix() + " ")) {
            return onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }
        // 解析 token
        String token = authHeader.substring((jwtProperties.getTokenPrefix() + " ").length());
        //System.out.println("【Gateway】Extracted token: " + token);
        try {
            Claims claims = jwtParser.parseToken(token);
            String jti = claims.getId();
            System.out.println("【Gateway】Parsed jti: " + jti);
            if (jti == null) {
                return onError(exchange, "Token missing jti claim", HttpStatus.UNAUTHORIZED);
            }
            // 4. 检查类型
            String type = (String) claims.get("type");
            if ("refresh".equals(type)) {
                // 刷新令牌不能用于 API 访问
                return onError(exchange, "Refresh token cannot be used for API access", HttpStatus.UNAUTHORIZED);
            }
            // 3. 检查黑名单
            if (redisService.hasKey(BLACKLIST_PREFIX + jti)) {
                return onError(exchange, "Token has been invalidated", HttpStatus.UNAUTHORIZED);
            }
            // 3. 【关键修改】检查白名单：token 是否仍在 Redis 中（即未登出且未过期）,暂时删除
//            String tokenKey = "auth:token:" + jti;
//            // 安全地调用阻塞式 RedisService
//            return Mono.fromCallable(() -> redisService.hasKey(tokenKey))
//                    .subscribeOn(Schedulers.boundedElastic()) // 切换到弹性线程执行阻塞操作
//                    .flatMap(exists -> {
//                        if (!exists) {
//                            return onError(exchange, "Token not found or already logged out", HttpStatus.UNAUTHORIZED);
//                        }
//                        // 透传用户信息
//                        ServerHttpRequest newRequest = exchange.getRequest().mutate()
//                                .header("X-User-ID", claims.getSubject().toString())
//                                .header("X-User-Name", claims.get("username").toString())
//                                .build();
//                        return chain.filter(exchange.mutate().request(newRequest).build());
//                    });
            //2026-01-19 【新增】权限校验
            String requiredPermission = getRequiredPermission(path);
            if (requiredPermission != null) {
                // 从 JWT claims 中获取用户权限列表
                Object permsObj = claims.get("permissions");
                List<String> userPermissions = new ArrayList<>();

                if (permsObj instanceof List) {
                    // 确保元素是 String
                    for (Object item : (List<?>) permsObj) {
                        if (item instanceof String) {
                            userPermissions.add((String) item);
                        }
                    }
                }

                // 校验是否包含所需权限
                if (!userPermissions.contains(requiredPermission)) {
                    return onError(exchange, "权限不足: 缺少 [" + requiredPermission + "]", HttpStatus.FORBIDDEN);
                }
            }

            //不再检查白名单！直接放行
            ServerHttpRequest newRequest = request.mutate()
                    //.header("X-User-ID", claims.getSubject())          // subject = username
                    .header("X-User-ID", claims.get("userId").toString())
                    .header("X-User-Name", (String) claims.get("username"))
                    .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("【Gateway】Error parsing token: " + e.getMessage());
            return onError(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 使用 AntPathMatcher 支持 /**, /* 等通配符
     */
    private boolean isExcludePath(String requestPath) {
        for (String whitePath : WHITE_LIST) {
            if (pathMatcher.match(whitePath, requestPath)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String body = "{\"error\": \"" + message + "\"}";
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    /**
     *  初始化权限映射表
     */
    @PostConstruct
    public void initPermissionMap() {
        PATH_PERMISSION_MAP.put("/api/coupon/create", "coupon:create");
        PATH_PERMISSION_MAP.put("/api/coupon/list", "coupon:read");
        PATH_PERMISSION_MAP.put("/api/order/create", "order:create");
        PATH_PERMISSION_MAP.put("/api/user/**", "user:read");
        // 内部接口：只允许服务间调用（可单独处理）
       // PATH_PERMISSION_MAP.put("/api/coupon/internal/**", "system:internal");
    }
    /**
     * 根据请求路径匹配所需的权限编码
     */
    private String getRequiredPermission(String requestPath) {
        for (Map.Entry<String, String> entry : PATH_PERMISSION_MAP.entrySet()) {
            if (pathMatcher.match(entry.getKey(), requestPath)) {
                return entry.getValue();
            }
        }
        return null; // 无需权限
    }
    @Override
    public int getOrder() {
        return -100; // 数值越小，优先级越高
    }
}
