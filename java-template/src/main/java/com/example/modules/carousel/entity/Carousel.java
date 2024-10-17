package com.example.modules.carousel.entity;

import com.example.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * (Carousel)实体类
 *
 * @author oceans
 * @since 2022-06-18 15:27:08
 */
@Data
public class Carousel extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 637825620538767092L;
    /**
    * 图片
    */
    private String path;
    /**
    * 名称
    */
    private String name;
    /**
    * 描述
    */
    private String describes;
    /**
    * 是否正在使用
    */
    private Boolean enabled = true;

}
