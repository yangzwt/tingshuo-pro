package com.tingshuo.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * packageName com.tingshuo.gateway.filter
 *
 * @author tingshuo
 * @version JDK 8
 * @className JwtParser
 * @date 2026/1/2 17:37
 * @description 类描述信息 JWT解析器
 */

public class JwtParser {

    private final String secret;

    public JwtParser(String secret) {
        this.secret = secret;
    }

    public Claims parseToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            System.out.println("【Gateway】使用 key 长度: " + key.getEncoded().length + " 字节");
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                System.err.println("【Gateway】Token 格式错误: " + token);
                return null;
            }

            String headerJson = new String(java.util.Base64.getUrlDecoder().decode(parts[0]));
            System.out.println("【Gateway】Token Header: " + headerJson);

            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
            System.out.println("【Gateway】Token Payload: " + payloadJson);

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            System.err.println("【Gateway】❌ 验签失败 - 签名不匹配: " + e.getMessage());
            return null;
        } catch (MalformedJwtException e) {
            System.err.println("【Gateway】❌ 验签失败 - Token 格式错误: " + e.getMessage());
            return null;
        } catch (ExpiredJwtException e) {
            System.err.println("【Gateway】❌ 验签失败 - Token 已过期: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("【Gateway】❌ 验签失败 - 其他错误: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public boolean validateToken(String token) {
        try {
            System.out.println("【Gateway】开始验证 token: " + token.substring(0, 50) + "...");

            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
