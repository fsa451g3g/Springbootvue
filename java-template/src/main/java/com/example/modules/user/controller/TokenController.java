package com.example.modules.user.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.config.SecurityConfig;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.modules.user.dao.RoleDao;
import com.example.modules.user.dto.TokenRequest;
import com.example.modules.user.entity.User;
import com.example.result.Result;
import com.example.modules.user.dao.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "获取token")
@RestController
@RequestMapping("token")
public class TokenController {

    UserDao userDao;
    PasswordEncoder passwordEncoder;
    RoleDao roleDao;

    @ApiOperation(value = "登录接口获取token")
    @PostMapping
    public Result token(@RequestBody TokenRequest tokenRequest) {
        User user = userDao.selectOne(new QueryWrapper<User>().eq("username", tokenRequest.getUsername()));
        if (!passwordEncoder.matches(tokenRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.getEnabled()) {
            throw new CustomException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.getAccountNonLocked()) {
            throw new CustomException(ExceptionType.USER_LOCKED);
        }
        if (!roleDao.getRolesByUserId(user.getId()).getName().equals(tokenRequest.getRole())) {
            throw new CustomException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        String jwt = SecurityConfig.TOKEN_PREFIX + JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwt);
        return Result.success(map);
    }

    @Autowired
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
