package com.example.modules.carousel.service;

import com.example.modules.carousel.dto.CarouselRequest;
import com.example.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * (Carousel)表服务接口
 *
 * @author oceans
 * @since 2022-06-18 15:27:09
 */
public interface CarouselService {

    Result upload(MultipartFile file);

    Result list(Long pageNum, Long pageSize, String search1, String search2);

    Result post(CarouselRequest carouselRequest);

    Result put(String id);

    Result delete(String id);

    Result get(String id);

    Result list();

}
