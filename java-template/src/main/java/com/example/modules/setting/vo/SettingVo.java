package com.example.modules.setting.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * (Setting)实体类
 *
 * @author oceans
 * @since 2022-06-22 09:55:24
 */
@Data
public class SettingVo implements Serializable {

    private String id;

    private String title;

    private String name;

    private String currentValue;

    private String placeholder;

}
