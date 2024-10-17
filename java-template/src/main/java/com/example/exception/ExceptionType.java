package com.example.exception;

public enum ExceptionType {

    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "请求错误"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"),
    USER_NAME_DUPLICATE(40001001, "用户名重复"),
    USER_NOT_FOUND(40401002, "用户不存在"),
    USER_PASSWORD_NOT_MATCH(40001003, "用户名或密码错误"),
    USER_NOT_ENABLED(40001004, "用户未启用"),
    USER_LOCKED(40001005, "用户被锁定"),
    SYSTEM_LOG_NOT_FOUND(40002001, "该日志不存在"),
    RUN_LOG_DELETE_FAIL(40002002, "运行日志删除失败"),
    FILE_UPLOAD_FAIL(40003001, "文件上传失败"),
    FILE_SUFFIX_NOT_EMPTY(40003002, "文件后缀不能为空"),
    FILE_SUFFIX_NOT_CORRECT(40003003, "文件后缀名不符合要求"),
    FILE_NOT_IMAGE(40003004, "文件不是图片"),
    FILE_COPY_FAIL(40003005, "文件拷贝失败"),
    FILE_DELETE_FAIL(40003006, "文件删除失败"),
    CAROUSEL_NOT_FOUND(40404001, "图片不存在"),
    SETTING_NOT_FOUND(40405001, "配置参数不存在"),
    SETTING_IS_IDENTICAL(40405002, "配置参数修改值与当前值相同");

    private final Integer code;

    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
