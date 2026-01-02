package com.tingshuo.auth.biz;
import java.util.Base64;
/**
 * packageName com.tingshuo.auth.biz
 *
 * @author tingshuo
 * @version JDK 8
 * @className GenerateSecreTest
 * @date 2026/1/2 16:36
 * @description 类描述信息 生成密钥测试类
 */
public class GenerateSecreTest {
    public static void main(String[] args) {
        String rawSecret = "tingshuo-secret-key-2026"; // 你的原始密钥
        String base64Secret = Base64.getEncoder().encodeToString(rawSecret.getBytes());
        System.out.println("Base64 Secret: " + base64Secret);
        }
    }
