package com.example.modules.log.service;

import com.example.result.Result;

/**
 * (SystemLog)表服务接口
 *
 * @author oceans
 * @since 2022-06-16 11:16:13
 */
public interface SystemLogService {

    Result list(Long pageNum, Long pageSize, String search1, String search2);

    Result delete(String ids);

    Result get(String id);
}
