package com.example.modules_admin.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.config.SecurityConfig;
import com.example.modules_admin.dao.UserDao;
import com.example.modules_admin.dto.TokenRequest;
import com.example.modules_admin.entity.User;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public Result token(@RequestBody TokenRequest tokenRequest) {
        User user = userDao.selectOne(new QueryWrapper<User>().eq("username", tokenRequest.getUsername()));
        if (user == null) {
            throw new RuntimeException("账号不存在");
        }
        if (!passwordEncoder.matches(tokenRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        if (!user.getEnabled()) {
            throw new RuntimeException("账号未启用");
        }
        if (!user.getAccountNonLocked()) {
            throw new RuntimeException("账号被锁定");
        }
        String jwt = SecurityConfig.TOKEN_PREFIX + JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwt);
        map.put("role", user.getRole());
        return Result.success("登录成功", map);
    }
}
