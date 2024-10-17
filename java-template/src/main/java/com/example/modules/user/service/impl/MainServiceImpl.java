package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.modules.user.dao.UserDao;
import com.example.modules.user.entity.User;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.service.MainService;
import com.example.result.Result;
import com.example.modules.user.dto.MainRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("mainServiceImpl")
public class MainServiceImpl implements MainService {

    UserMapper userMapper;
    UserDao userDao;
    PasswordEncoder passwordEncoder;

    @Override
    public Result post(MainRequest mainRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userMapper.toEntity(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, authentication.getName())), mainRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateById(user);
        return Result.success("修改个人信息成功");
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
