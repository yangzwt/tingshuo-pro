package com.tingshuo.auth.api.feign.fallback;

import com.tingshuo.auth.api.dto.LoginDTO;
import com.tingshuo.auth.api.dto.MenuDTO;
import com.tingshuo.auth.api.feign.AuthApi;

import com.tingshuo.auth.api.vo.MenuVO;
import com.tingshuo.auth.api.vo.UserVO;
import com.tingshuo.common.core.web.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.tingshuo.auth.api.feign.fallback
 *
 * @author tingshuo
 * @version JDK 8
 * @className AuthApiFallback
 * @date 2026/1/2 14:56
 * @description 类描述信息
 */
public class AuthApiFallback implements AuthApi {
    @Override
    public Result<String> login(LoginDTO request) {
        return Result.fail("登录服务不可用");
    }

    @Override
    public Result<UserVO> getUserInfo(String token) {
        return Result.fail("未登录");
    }

    @Override
    public Result<List<MenuVO>> getMenuList(Long userId) {
        return Result.success(new ArrayList<>());
    }
}
