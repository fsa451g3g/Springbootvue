package com.example.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.modules.log.dao.SystemLogDao;
import com.example.modules.log.entity.SystemLog;
import com.example.modules.log.service.SystemLogService;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * (SystemLog)表服务实现类
 *
 * @author oceans
 * @since 2022-06-16 11:16:15
 */
@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {

    SystemLogDao systemLogDao;

    @Override
    public Result list(Long pageNum, Long pageSize, String search1, String search2) {
        Map<String, Object> map = new HashMap<>();
        Page<SystemLog> all = systemLogDao.selectPage(PageDTO.of(pageNum, pageSize), new LambdaQueryWrapper<SystemLog>()
                .like(!search1.equals(""), SystemLog::getUsername, search1));
        map.put("list", all.getRecords());
        map.put("total", all.getTotal());
        return Result.success("获取系统日志成功", map);
    }

    @Override
    public Result delete(String ids) {
        if (ids.equals("0")) {
            systemLogDao.delete(new QueryWrapper<>());
        } else {
            systemLogDao.deleteBatchIds(Arrays.asList(ids.split(",")));
        }
        return Result.success("删除日志成功");
    }

    @Override
    public Result get(String id) {
        SystemLog systemLog = systemLogDao.selectById(id);
        if (systemLog == null) {
            throw new CustomException(ExceptionType.SYSTEM_LOG_NOT_FOUND);
        }
        return Result.success("查询成功", systemLog);
    }


    @Autowired
    public void setSystemLogDao(SystemLogDao systemLogDao) {
        this.systemLogDao = systemLogDao;
    }

}
