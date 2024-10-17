package com.example.modules.user.service;

import com.example.result.Result;
import com.example.modules.user.dto.MainRequest;

public interface MainService {
    Result post(MainRequest mainRequest);
}
