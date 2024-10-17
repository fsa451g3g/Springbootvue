package com.example.modules.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {

    private String id;

    private String username;

    private String nickname;

    private String tel;

    private String email;

    private Boolean accountNonLocked;

    private Date createdTime;
}
