package com.example.modules.log.service;

import com.example.result.Result;
import jakarta.servlet.http.HttpServletResponse;


public interface RunLogService {

    Result list(Integer pageNum, Integer pageSize);

    Result delete(String id);

    Result download(String fileName, HttpServletResponse response);
}
