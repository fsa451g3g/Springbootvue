package com.example.modules_admin.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2024-10-13 17:39:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("serial")
public class User extends Model<User> {
//用户ID
    private String id;
//用户名
    private String username;
//昵称
    private String nickname;
//密码
    private String password;
//角色
    private String role;
//电话号码
    private String tel;
//邮箱
    private String email;
//是否未锁定，1-是，0-否
    private Boolean accountNonLocked;
//是否启用，1-是，0-否
    private Boolean enabled;
//创建时间
    private Date createdTime;
//更新时间
    private Date updatedTime;
}

