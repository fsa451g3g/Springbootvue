package com.example.modules.setting.controller;

import com.example.modules.setting.dto.SettingRequest;
import com.example.modules.setting.service.SettingService;
import com.example.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * (Setting)表控制层
 *
 * @author oceans
 * @since 2022-06-22 09:55:26
 */
@RestController
@RequestMapping("setting")
public class SettingController {

    SettingService settingService;

    @ApiOperation(value = "根据名称获取配置信息")
    @GetMapping("{name}")
    public Result get(@PathVariable String name) {
        return settingService.get(name);
    }

    @ApiOperation(value = "根据id修改配置信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public Result post(@RequestBody SettingRequest settingRequest) {
        return settingService.post(settingRequest);
    }

    @ApiOperation(value = "根据名称还原配置信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("{name}")
    public Result put(@PathVariable String name) {
        return settingService.put(name);
    }

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }
}
