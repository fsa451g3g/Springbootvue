package com.example.modules.menu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表(Menu)实体类
 *
 * @author oceans
 * @since 2022-06-07 17:40:43
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 427326164769230463L;
    /**
    * 菜单ID
    */
    @TableId
    private Integer id;
    /**
    * 菜单编码
    */
    private String code;
    /**
    * 菜单名称
    */
    private String title;
    /**
    * 父菜单
    */
    private Integer fatherId;
    /**
    * 路径
    */
    private String path;
    /**
    * icon
    */
    private String icon;
    /**
    * 是否有子菜单
    */
    private Integer isHasSon;
    /**
    * 是否是菜单,true:是，false：否
    */
    private Boolean isMenu;
    /**
    * 组件名称
    */
    private String component;
}
