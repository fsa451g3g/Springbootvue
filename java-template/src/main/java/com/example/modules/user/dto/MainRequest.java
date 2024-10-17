package com.example.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author oceans
 * @since 2022-06-07 12:40:07
 */
@ApiModel(value = "修改个人信息请求实体类")
@Data
public class MainRequest implements Serializable {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "姓名",required = true)
    private String nickname;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "电话号码",required = true)
    private String tel;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

}
