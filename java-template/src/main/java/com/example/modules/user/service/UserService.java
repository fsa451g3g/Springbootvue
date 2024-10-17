package com.example.modules.user.service;

import com.example.modules.user.entity.LoginUser;
import com.example.result.Result;
import com.example.modules.user.dto.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户表(User)表服务接口
 *
 * @author oceans
 * @since 2022-06-07 12:24:48
 */
public interface UserService extends UserDetailsService {

    @Override
    LoginUser loadUserByUsername(String username) throws UsernameNotFoundException;

    Result list(Long pageNum, Long pageSize, String search1, String search2, String roleId);

    Result get(String id);

    Result post(UserRequest userRequest);

    Result delete(String id);

    Result put(String id);

    Result me();

}
