package com.example.modules.carousel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.carousel.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Carousel)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-18 15:27:09
 */
@Mapper
public interface CarouselDao extends BaseMapper<Carousel> {


}
