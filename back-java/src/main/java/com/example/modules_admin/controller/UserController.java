package com.example.modules_admin.controller;

import com.example.modules_admin.service.UserService;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表(User)表控制层
 *
 * @author oceans
 * @since 2022-06-07 12:24:48
 */

@RestController
@RequestMapping("admin")
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping("list/{pageNum}/{pageSize}/{roleId}")
//    public Result list(@PathVariable Long pageNum, @PathVariable Long pageSize, @PathVariable String roleId,
//                       String search1, String search2) {
//        return userService.list(pageNum, pageSize, search1, search2, roleId);
//    }

//    @GetMapping("{id}")
//    public Result get(@PathVariable String id) {
//        return userService.get(id);
//    }

//    @PostMapping
//    public Result post(@Validated @RequestBody UserRequest userRequest) {
//        return userService.post(userRequest);
//    }

//    @DeleteMapping("{id}")
//    public Result delete(@PathVariable String id) {
//        return userService.delete(id);
//    }

//    @PutMapping("{id}")
//    public Result put(@PathVariable String id) {
//        return userService.put(id);
//    }

    @GetMapping("me")
    public Result me() {
        return userService.me();
    }
}
