package com.example.modules.setting.service;

import com.example.modules.setting.dto.SettingRequest;
import com.example.result.Result;

/**
 * (Setting)表服务接口
 *
 * @author oceans
 * @since 2022-06-22 09:55:26
 */
public interface SettingService {


    Result get(String name);

    Result post(SettingRequest settingRequest);

    Result put(String name);
}
