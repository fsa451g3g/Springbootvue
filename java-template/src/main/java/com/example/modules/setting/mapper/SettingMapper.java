package com.example.modules.setting.mapper;

import com.example.modules.setting.entity.Setting;
import com.example.modules.setting.vo.SettingVo;
import org.mapstruct.Mapper;

/**
 * (Setting)表数据库访问层
 *
 * @author oceans
 * @since 2022-06-22 09:55:27
 */
@Mapper(componentModel = "spring")
public interface SettingMapper {


    SettingVo toVo(Setting setting);

}
