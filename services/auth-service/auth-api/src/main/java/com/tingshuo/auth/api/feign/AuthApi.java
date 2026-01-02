package com.tingshuo.auth.api.feign;

import com.tingshuo.auth.api.dto.LoginDTO;
import com.tingshuo.auth.api.feign.fallback.AuthApiFallback;

import com.tingshuo.auth.api.vo.MenuVO;
import com.tingshuo.auth.api.vo.UserVO;
import com.tingshuo.common.core.web.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName com.tingshuo.auth.api.feign
 *
 * @author tingshuo
 * @version JDK 8
 * @className AuthApi
 * @date 2026/1/2 14:47
 * @description 接口描述信息
 */
@FeignClient(name = "auth-service", fallback = AuthApiFallback.class)
public interface AuthApi {
    @PostMapping("/api/auth/login")
    Result<String> login(@RequestBody LoginDTO request);

    @GetMapping("/api/auth/user/info")
    Result<UserVO> getUserInfo(@RequestParam("token") String token);

    @GetMapping("/api/auth/menu/list")
    Result<List<MenuVO>> getMenuList(@RequestParam("userId") Long userId);
}
