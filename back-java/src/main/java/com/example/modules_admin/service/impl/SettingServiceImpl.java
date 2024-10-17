package com.example.modules_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.modules_admin.dao.SettingDao;
import com.example.modules_admin.entity.Setting;
import com.example.modules_admin.mapper.SettingMapper;
import com.example.modules_admin.service.SettingService;
import com.example.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Setting)表服务实现类
 *
 * @author oceans
 * @since 2022-06-22 09:55:26
 */
@Service("settingService")
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingDao settingDao;
    @Autowired
    SettingMapper settingMapper;

    @Override
    public Result get(String name) {
        return Result.success("查询成功", getSetting(null, name));
    }

//    @Override
//    public Result post(SettingRequest settingRequest) {
//        Setting setting = getSetting(settingRequest.getId(), null);
//        if (settingRequest.getCurrentValue().equals(setting.getCurrentValue()))
//            throw new CustomException(ExceptionType.SETTING_IS_IDENTICAL);
//        setting.setCurrentValue(settingRequest.getCurrentValue());
//        settingDao.updateById(setting);
//        return Result.success("配置修改成功");
//    }
//
//    @Override
//    public Result put(String name) {
//        Setting setting = getSetting(null, name);
//        setting.setCurrentValue(setting.getInitialValue());
//        settingDao.updateById(setting);
//        return Result.success("配置参数已还原");
//    }

    public Setting getSetting(String id, String name) {
        Setting setting = settingDao.selectOne(new LambdaQueryWrapper<Setting>().eq(id != null, Setting::getId, id)
                .eq(name != null, Setting::getName, name));
        if (setting == null) throw new RuntimeException("设置不存在");
        return setting;
    }


}
