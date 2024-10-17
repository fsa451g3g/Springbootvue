package com.example.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleDao extends BaseMapper<Role> {

    @Select("select role.id,role.name,role.title,role.authority " +
            " from role left join user_role on role.id = user_role.role_id where user_role.user_id = #{userId}")
    Role getRolesByUserId(String userId);

}
