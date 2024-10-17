package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.modules.user.dao.RoleDao;
import com.example.modules.user.dao.UserDao;
import com.example.modules.user.entity.LoginUser;
import com.example.modules.user.entity.User;
import com.example.modules.user.mapper.UserMapper;
import com.example.result.Result;
import com.example.modules.user.dto.UserRequest;
import com.example.modules.user.service.UserService;
import com.example.modules.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author oceans
 * @since 2022-06-07 12:24:48
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    UserDao userDao;
    UserMapper userMapper;
    RoleDao roleDao;
    PasswordEncoder passwordEncoder;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(null, username);
        LoginUser loginUser = userMapper.toLoginUser(user);
        loginUser.setRole(roleDao.getRolesByUserId(user.getId()));
        return loginUser;
    }

    @Override
    public Result list(Long pageNum, Long pageSize, String search1, String search2, String roleId) {
        Map<String, Object> map = new HashMap<>();
        List<String> userIds = userDao.getIdsByRoleId(roleId);
        Page<User> all = userDao.selectPage(PageDTO.of(pageNum, pageSize), new LambdaQueryWrapper<User>().like(!search1.equals(""), User::getUsername, search1)
                .like(!search2.equals(""), User::getNickname, search2).in(User::getId, userIds).eq(User::getEnabled, true));
        List<UserVo> list = all.getRecords().stream().map(userMapper::toVo).collect(Collectors.toList());
        map.put("list", list);
        map.put("total", all.getTotal());
        return Result.success("获取用户列表成功", map);
    }

    @Override
    public Result get(String id) {
        User user = getUser(id, null);
        return Result.success("查询用户成功", userMapper.toVo(user));
    }

    @Override
    public Result post(UserRequest userRequest) {
        if (userRequest.getId().equals("0")) {
            userRequest.setId(null);
            User user = userMapper.toEntity(new User(), userRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.insert(user);
            userDao.addUserRole(user.getId(), userRequest.getRoleId());
            return Result.success("添加用户成功");
        } else {
            User user = userMapper.toEntity(getUser(userRequest.getId(), null), userRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.updateById(user);
            return Result.success("修改用户成功");
        }
    }

    @Override
    public Result delete(String id) {
        User user = getUser(id, null);
        user.setEnabled(false);
        userDao.updateById(user);
        return Result.success("删除账号成功");
    }

    @Override
    public Result put(String id) {
        User user = getUser(id, null);
        user.setAccountNonLocked(!user.getAccountNonLocked());
        userDao.updateById(user);
        return Result.success("修改用户状态成功");
    }

    @Override
    public Result me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUser(null, authentication.getName());
        return Result.success("查询当前用户成功", user);
    }

    public User getUser(String id, String username) {
        User user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(id != null, User::getId, id)
                .eq(username != null, User::getUsername, username)
                .eq(User::getEnabled, true));
        if (user == null) throw new CustomException(ExceptionType.USER_NOT_FOUND);
        return user;
    }

    @Autowired
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
