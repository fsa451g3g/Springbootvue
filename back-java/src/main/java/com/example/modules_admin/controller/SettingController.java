package com.example.modules_admin.controller;

import com.example.modules_admin.service.SettingService;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Setting)表控制层
 *
 * @author oceans
 * @since 2022-06-22 09:55:26
 */
@RestController
@RequestMapping("/admin/setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    @GetMapping("{name}")
    public Result get(@PathVariable String name) {
        return settingService.get(name);
    }

//    @PostMapping
//    public Result post(@RequestBody SettingRequest settingRequest) {
//        return settingService.post(settingRequest);
//    }
//
//    @PutMapping("{name}")
//    public Result put(@PathVariable String name) {
//        return settingService.put(name);
//    }

}
