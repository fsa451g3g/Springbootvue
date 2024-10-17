package com.example.modules.setting.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Setting)实体类
 *
 * @author oceans
 * @since 2022-06-22 09:55:24
 */
@Data
public class Setting implements Serializable {
    private static final long serialVersionUID = 266123676300578004L;
    /**
    * 主键
    */
    private String id;
    /**
    * 标题
    */
    private String title;
    /**
    * 名称
    */
    private String name;
    /**
    * 当前值
    */
    private String currentValue;
    /**
    * 初始值
    */
    private String initialValue;
    /**
    * 提示语
    */
    private String placeholder;

}
