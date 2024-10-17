package com.example.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-07 12:24:48
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    @Insert("insert into user_role(user_id,role_id) values (#{userId},#{roleId})")
    void addUserRole(String userId, String roleId);

    @Select("select user_role.user_id from user_role where role_id = #{roleId}")
    List<String> getIdsByRoleId(String roleId);

}
