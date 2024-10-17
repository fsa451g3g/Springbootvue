package com.example.modules.carousel.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * (Carousel)实体类
 *
 * @author oceans
 * @since 2022-06-18 15:27:08
 */
@Data
public class CarouselVo implements Serializable {
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

}
