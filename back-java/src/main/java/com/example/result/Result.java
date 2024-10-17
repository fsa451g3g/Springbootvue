package com.example.result;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result {

    private Integer code;

    private String message;

    private Object data;

    public static Result build() {
        return new Result();
    }

    public static Result success() {
        return Result.build().setCode(200).setMessage("操作成功！");
    }

    public static Result success(Integer code) {
        return Result.build().setCode(code).setMessage("操作成功！");
    }

    public static Result success(String message) {
        return Result.build().setCode(200).setMessage(message);
    }

    public static Result success(Object data) {
        return Result.build().setCode(200).setMessage("操作成功！").setData(data);
    }

    public static Result success(String message,Object data) {
        return Result.build().setCode(200).setMessage(message).setData(data);
    }

    public static Result success(Integer code,Object data) {
        return Result.build().setCode(code).setMessage("操作成功！").setData(data);
    }

    public static Result success(Integer code,String message) {
        return Result.build().setCode(code).setMessage(message);
    }


    public static Result success(Integer code,String message,Object data) {
        return Result.build().setCode(code).setMessage(message).setData(data);
    }

    public static Result failure() {
        return Result.build().setCode(400).setMessage("操作失败！");
    }

    public static Result failure(Integer code) {
        return Result.build().setCode(code).setMessage("操作失败！");
    }

    public static Result failure(String message) {
        return Result.build().setCode(400).setMessage(message);
    }

    public static Result failure(Object data) {
        return Result.build().setCode(400).setMessage("操作失败！").setData(data);
    }

    public static Result failure(String message,Object data) {
        return Result.build().setCode(400).setMessage(message).setData(data);
    }

    public static Result failure(Integer code,Object data) {
        return Result.build().setCode(code).setMessage("操作失败！").setData(data);
    }

    public static Result failure(Integer code,String message) {
        return Result.build().setCode(code).setMessage(message);
    }


    public static Result failure(Integer code,String message,Object data) {
        return Result.build().setCode(code).setMessage(message).setData(data);
    }


}
