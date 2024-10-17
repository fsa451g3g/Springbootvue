package com.example.modules_admin.service;

import com.example.modules_admin.entity.LoginUser;
import com.example.result.Result;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    LoginUser loadUserByUsername(String username) throws UsernameNotFoundException;

    /*Result list(Long pageNum, Long pageSize, String search1, String search2, String roleId);

    Result get(String id);

    Result post(UserRequest userRequest);

    Result delete(String id);

    Result put(String id);*/

    Result me();

}