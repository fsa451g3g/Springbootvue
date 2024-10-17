package com.example.modules_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules_admin.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {


}