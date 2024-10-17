package com.example.modules_admin.mapper;


import com.example.modules_admin.entity.LoginUser;
import com.example.modules_admin.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    LoginUser toLoginUser(User user);

    /*UserVo toVo(User user);

    User toEntity(@MappingTarget User user, UserRequest userRequest);

    User toEntity(@MappingTarget User user, MainRequest mainRequest);*/

}