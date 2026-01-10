package com.tingshuo.gateway.filter;

import com.tingshuo.gateway.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * packageName com.tingshuo.gateway.filter
 *
 * @author tingshuo
 * @version JDK 8
 * @className JwtParser
 * @date 2026/1/2 17:37
 * @description 类描述信息 JWT解析器
 */
@Component
public class JwtParser {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     *  解析Token
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token", e);
        }
    }

    /**
     *  获取密钥
     * @return
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret().trim());
        //System.out.println("【Gateway】原始 secret 长度: " + keyBytes.length + " 字节");
        //System.out.println("【SERVICE】密钥 Hex: " + bytesToHex(keyBytes));
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
