package com.example.modules.menu.controller;

import com.example.modules.menu.service.MenuService;
import com.example.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单表(Menu)表控制层
 *
 * @author oceans
 * @since 2022-06-07 17:40:45
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("menu")
public class MenuController {

    MenuService menuService;

    @ApiOperation(value = "获取权限列表")
    @GetMapping("list")
    public Result list() {
        return menuService.list();
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
