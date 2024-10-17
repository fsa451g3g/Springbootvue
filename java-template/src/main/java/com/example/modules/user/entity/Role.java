package com.example.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author oceans
 * @since 2022-06-07 12:56:10
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -51704885093219729L;
    /**
    * 角色ID
    */
    @TableId
    private String id;
    /**
    * 角色名称
    */
    private String name;
    /**
    * 角色标识
    */
    private String title;
    /**
    * 角色权限
    */
    private String authority;
}
