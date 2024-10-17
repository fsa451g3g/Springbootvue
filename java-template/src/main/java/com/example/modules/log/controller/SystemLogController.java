package com.example.modules.log.controller;

import com.example.modules.log.service.SystemLogService;
import com.example.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * (SystemLog)表控制层
 *
 * @author oceans
 * @since 2022-06-16 11:16:15
 */
@Api(tags = "系统日志")
@RestController
@RequestMapping("systemLog")
public class SystemLogController {

    SystemLogService systemLogService;

    @ApiOperation(value = "获取系统日志列表")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Long pageNum, @PathVariable Long pageSize,
                       String search1, String search2) {
        return systemLogService.list(pageNum, pageSize, search1, search2);
    }

    @ApiOperation(value = "删除系统日志")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping
    public Result delete(String ids) {
        return systemLogService.delete(ids);
    }

    @ApiOperation(value = "按照ID查询系统日志")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public Result get(@PathVariable String id) {
        return systemLogService.get(id);
    }

    @Autowired
    public void setSystemLogService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }
}
