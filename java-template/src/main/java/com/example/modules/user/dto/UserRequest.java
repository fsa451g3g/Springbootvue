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
@ApiModel(value = "账号管理请求实体类")
@Data
public class UserRequest implements Serializable {

    @ApiModelProperty(value = "ID", required = true,example = "添加用户请设置ID为0")
    private String id;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "姓名", required = true)
    private String nickname;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "电话号码", required = true)
    private String tel;

    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @ApiModelProperty(value = "角色ID", required = true, example = "1或者2")
    private String roleId;

}
