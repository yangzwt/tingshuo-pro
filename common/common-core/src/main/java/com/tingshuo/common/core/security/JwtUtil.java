package com.tingshuo.common.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * packageName com.tingshuo.common.core.security
 *
 * @author tingshuo
 * @version JDK 8
 * @className JwtUtil
 * @date 2026/1/1 17:13
 * @description 类描述信息 JWT工具类
 */
@Component
@Data
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret.trim());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     *  生成令牌
     * @param claims
     * @param subject
     * @return
     */
    public String generateToken(Map<String, Object> claims, String subject) {
        String jti = UUID.randomUUID().toString(); // 唯一令牌ID
        return Jwts.builder()
                .setId(jti) // 令牌 ID
                //.setClaims(claims) // 添加自定义信息 会导致 ID 存储不在情况
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     *  解析令牌
     * @param token
     * @return
     */
    public Claims parseClaims(String token) {
        try {
             return  Jwts.parser()
                            .setSigningKey(getSigningKey())
                            .parseClaimsJws(token)
                            .getBody();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    /**
     * 提供获取 jti 的方法
     * @param token
     * @return
     */
    public String getJtiFromToken(String token) {
            Claims claims = parseClaims(token);
            if (claims==null){
                return null;
            }
         return claims.getId();
    }
}
