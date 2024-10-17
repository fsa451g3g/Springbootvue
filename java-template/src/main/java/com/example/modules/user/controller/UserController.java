package com.example.modules.user.controller;

import com.example.result.Result;
import com.example.modules.user.dto.UserRequest;
import com.example.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(User)表控制层
 *
 * @author oceans
 * @since 2022-06-07 12:24:48
 */

@Api(tags = "账号管理")
@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    @ApiOperation(value = "获取账号列表")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("list/{pageNum}/{pageSize}/{roleId}")
    public Result list(@PathVariable Long pageNum, @PathVariable Long pageSize, @PathVariable String roleId,
                       String search1, String search2) {
        return userService.list(pageNum, pageSize, search1, search2, roleId);
    }

    @ApiOperation(value = "根据ID获取账号信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public Result get(@PathVariable String id) {
        return userService.get(id);
    }

    @ApiOperation(value = "添加或者修改账号信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public Result post(@Validated @RequestBody UserRequest userRequest) {
        return userService.post(userRequest);
    }

    @ApiOperation(value = "根据ID删除账号")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return userService.delete(id);
    }

    @ApiOperation(value = "根据ID改变账号状态")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public Result put(@PathVariable String id) {
        return userService.put(id);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("me")
    public Result me() {
        return userService.me();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
