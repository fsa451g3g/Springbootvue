package com.example.modules.log.controller;

import com.example.modules.log.service.RunLogService;
import com.example.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Api(tags = "运行日志")
@RestController
@RequestMapping("runLog")
public class RunLogController {

    RunLogService runLogService;

    @ApiOperation(value = "获取运行日志列表")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return runLogService.list(pageNum, pageSize);
    }

    @ApiOperation(value = "删除运行日志")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return runLogService.delete(id);
    }

    @ApiOperation(value = "下载运行日志")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("download/{fileName}")
    public Result download(@PathVariable String fileName, HttpServletResponse response) {
        return runLogService.download(fileName, response);
    }

    @Autowired
    public void setRunLogService(RunLogService runLogService) {
        this.runLogService = runLogService;
    }
}
