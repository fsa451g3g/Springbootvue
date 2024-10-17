package com.example.modules.user.mapper;

import com.example.modules.user.entity.LoginUser;
import com.example.modules.user.entity.User;
import com.example.modules.user.dto.MainRequest;
import com.example.modules.user.dto.UserRequest;
import com.example.modules.user.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * 用户表(User)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-07 12:24:49
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    LoginUser toLoginUser(User user);

    UserVo toVo(User user);

    User toEntity(@MappingTarget User user, UserRequest userRequest);

    User toEntity(@MappingTarget User user, MainRequest mainRequest);

}
