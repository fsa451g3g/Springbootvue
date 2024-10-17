package com.example.modules_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules_admin.entity.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Setting)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-22 09:55:25
 */
@Mapper
public interface SettingDao extends BaseMapper<Setting> {

}
