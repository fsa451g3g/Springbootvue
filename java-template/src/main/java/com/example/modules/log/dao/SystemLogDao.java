package com.example.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.log.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (SystemLog)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-16 09:43:19
 */
@Mapper
public interface SystemLogDao extends BaseMapper<SystemLog> {

}
