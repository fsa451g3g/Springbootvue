package com.example.modules.carousel.controller;

import com.example.modules.carousel.dto.CarouselRequest;
import com.example.modules.carousel.service.CarouselService;
import com.example.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * (Carousel)表控制层
 *
 * @author oceans
 * @since 2022-06-18 15:27:10
 */
@Api(tags = "用户主页")
@RestController
@RequestMapping("carousel")
public class CarouselController {

    CarouselService carouselService;

    @ApiOperation(value = "上传轮播图图片")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file) {
        return carouselService.upload(file);
    }

    @ApiOperation(value = "获取用户主页轮播图列表")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Long pageNum, @PathVariable Long pageSize, String search1, String search2) {
        return carouselService.list(pageNum, pageSize, search1, search2);
    }

    @ApiOperation(value = "添加或者修改用户主页轮播图")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public Result post(@Validated @RequestBody CarouselRequest carouselRequest) {
        return carouselService.post(carouselRequest);
    }

    @ApiOperation(value = "根据ID查询图片信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public Result get(@PathVariable String id) {
        return carouselService.get(id);
    }

    @ApiOperation(value = "根据ID改变图片状态")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public Result put(@PathVariable String id) {
        return carouselService.put(id);
    }

    @ApiOperation(value = "根据ID删除图片")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return carouselService.delete(id);
    }

    @ApiOperation(value = "获取用户主页轮播图列表")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("list/user")
    public Result listUser() {
        return carouselService.list();
    }

    @Autowired
    public void setCarouselService(CarouselService carouselService) {
        this.carouselService = carouselService;
    }
}
