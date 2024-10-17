package com.example.modules.user.entity;

import com.example.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author oceans
 * @since 2022-06-07 12:40:07
 */
@Data
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 662443702739634025L;
    /**
    * 用户名
    */
    private String username;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 密码
    */
    private String password;
    /**
    * 电话号码
    */
    private String tel;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 是否未过期，1-是，0-否
    */
    private Boolean accountNonLocked = true;
    /**
    * 是否启用，1-是，0-否
    */
    private Boolean enabled = true;
}
