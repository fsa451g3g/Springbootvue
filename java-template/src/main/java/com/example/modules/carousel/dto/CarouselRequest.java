package com.example.modules.carousel.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * (Carousel)实体类
 *
 * @author oceans
 * @since 2022-06-18 15:27:08
 */
@Data
public class CarouselRequest implements Serializable {
    private String id;

    private String name;

    private String describes;

    private String tempName;
}
