package com.example.modules.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SystemLog)实体类
 *
 * @author oceans
 * @since 2022-06-16 09:43:18
 */
@Data
public class SystemLog implements Serializable {
    private static final long serialVersionUID = 586027409258691882L;
    /**
    * 日志id
    */
    @TableId
    private String id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 请求路径
    */
    private String url;
    /**
    * 请求方法
    */
    private String method;
    /**
    * 请求参数
    */
    private String args;
    /**
    * 结果
    */
    private Boolean result;
    /**
    * 异常信息
    */
    private String message;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    /**
    * 控制器方法
    */
    private String classMethod;
}
