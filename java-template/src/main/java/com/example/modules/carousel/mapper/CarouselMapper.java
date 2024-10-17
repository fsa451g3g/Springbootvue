package com.example.modules.carousel.mapper;

import com.example.modules.carousel.dto.CarouselRequest;
import com.example.modules.carousel.entity.Carousel;
import com.example.modules.carousel.vo.CarouselVo;
import org.mapstruct.Mapper;

/**
 * (Carousel)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-18 15:27:10
 */
@Mapper(componentModel = "spring")
public interface CarouselMapper {

    Carousel toEntity(CarouselRequest carouselRequest);

    CarouselVo toVo(Carousel carousel);

}
