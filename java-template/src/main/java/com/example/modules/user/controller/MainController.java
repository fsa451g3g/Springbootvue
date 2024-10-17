package com.example.modules.user.controller;

import com.example.modules.user.service.MainService;
import com.example.result.Result;
import com.example.modules.user.dto.MainRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "个人中心")
@RestController
@RequestMapping("main")
public class MainController {

    MainService mainService;

    @ApiOperation(value = "修改个人信息")
    @PostMapping
    public Result post(@Validated @RequestBody MainRequest mainRequest) {
        return mainService.post(mainRequest);
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }
}
