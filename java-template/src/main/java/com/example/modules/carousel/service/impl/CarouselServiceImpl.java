package com.example.modules.carousel.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.modules.carousel.dao.CarouselDao;
import com.example.modules.carousel.dto.CarouselRequest;
import com.example.modules.carousel.entity.Carousel;
import com.example.modules.carousel.mapper.CarouselMapper;
import com.example.modules.carousel.service.CarouselService;
import com.example.config.WebConfig;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Carousel)表服务实现类
 *
 * @author oceans
 * @since 2022-06-18 15:27:09
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    CarouselDao carouselDao;
    CarouselMapper carouselMapper;

    @Override
    public Result upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix = null;
        if (index == -1 || (suffix = fileName.substring(index + 1)).isEmpty()) {
            throw new CustomException(ExceptionType.FILE_SUFFIX_NOT_EMPTY);
        }
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
            throw new CustomException(ExceptionType.FILE_SUFFIX_NOT_CORRECT);
        }
        String tempName = IdUtil.simpleUUID() + "." + suffix;
        if (!new File(WebConfig.UPLOADFILEPATH).exists()) new File(WebConfig.UPLOADFILEPATH).mkdir();
        File tempFile = new File(WebConfig.UPLOADFILEPATH, tempName);
        try {
            file.transferTo(tempFile);
            try {
                BufferedImage bufferedImage = ImageIO.read(tempFile);
                bufferedImage.getWidth();
                bufferedImage.getHeight();
            } catch (Exception e) {
                throw new CustomException(ExceptionType.FILE_NOT_IMAGE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("tempName", tempName);
        return Result.success("上传成功", map);
    }

    @Override
    public Result list(Long pageNum, Long pageSize, String search1, String search2) {
        Map<String, Object> map = new HashMap<>();
        Page<Carousel> all = carouselDao.selectPage(PageDTO.of(pageNum, pageSize), new LambdaQueryWrapper<Carousel>().like(!search1.equals(""), Carousel::getName, search1)
                .like(!search2.equals(""), Carousel::getEnabled, search2));
        map.put("list", all.getRecords());
        map.put("total", all.getTotal());
        map.put("prefix", "carousel");
        return Result.success("获取用户列表成功", map);
    }

    @Override
    public Result post(CarouselRequest carouselRequest) {
        if (carouselRequest.getId().equals("0")) {
            if (!new File(WebConfig.CAROUSELPATH).exists()) new File(WebConfig.CAROUSELPATH).mkdir();
            try {
                FileCopyUtils.copy(new File(WebConfig.UPLOADFILEPATH + File.separator + carouselRequest.getTempName()),
                        new File(WebConfig.CAROUSELPATH + File.separator + carouselRequest.getTempName()));
            } catch (IOException e) {
                throw new CustomException(ExceptionType.FILE_COPY_FAIL);
            }
            Carousel carousel = carouselMapper.toEntity(carouselRequest);
            carousel.setId(null);
            carousel.setPath(carouselRequest.getTempName());
            carouselDao.insert(carousel);
            return Result.success("添加成功");
        }
        return null;
    }

    @Override
    public Result put(String id) {
        Carousel carousel = getCarousel(id);
        carousel.setEnabled(!carousel.getEnabled());
        carouselDao.updateById(carousel);
        return Result.success("修改图片状态成功");
    }

    @Override
    public Result delete(String id) {
        Carousel carousel = getCarousel(id);
        boolean delete = new File(WebConfig.CAROUSELPATH + File.separator + carousel.getPath()).delete();
        if (!delete) throw new CustomException(ExceptionType.FILE_DELETE_FAIL);
        carouselDao.deleteById(id);
        return Result.success("删除图片成功");
    }

    @Override
    public Result get(String id) {
        Carousel carousel = getCarousel(id);
        return Result.success("查询成功", carousel);
    }

    @Override
    public Result list() {
        Map<String, Object> map = new HashMap<>();
        List<Carousel> list = carouselDao.selectList(new LambdaQueryWrapper<Carousel>().eq(Carousel::getEnabled, true));
        map.put("list", list.stream().map(carouselMapper::toVo).collect(Collectors.toList()));
        map.put("prefix", "carousel");
        return Result.success("获取轮播图列表成功", map);
    }

    public Carousel getCarousel(String id) {
        Carousel carousel = carouselDao.selectById(id);
        if (carousel == null) throw new CustomException(ExceptionType.CAROUSEL_NOT_FOUND);
        return carousel;
    }

    @Autowired
    public void setCarouselDao(CarouselDao carouselDao) {
        this.carouselDao = carouselDao;
    }

    @Autowired
    public void setCarouselMapper(CarouselMapper carouselMapper) {
        this.carouselMapper = carouselMapper;
    }
}


