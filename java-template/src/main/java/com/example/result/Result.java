package com.example.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "响应体实体")
@Data
@Accessors(chain = true)
public class Result {

    @ApiModelProperty(value = "代码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Object data;

    public static Result build() {
        return new Result();
    }

    public static Result success() {
        return Result.build().setCode(200).setMessage("操作成功");
    }

    public static Result success(String message) {
        return Result.build().setCode(200).setMessage(message);
    }

    public static Result success(Object data) {
        return Result.build().setCode(200).setMessage("操作成功").setData(data);
    }

    public static Result success(String message, Object data) {
        return Result.build().setCode(200).setMessage(message).setData(data);
    }

    public static Result failure() {
        return Result.build().setCode(400).setMessage("操作失败");
    }

    public static Result failure(String message) {
        return Result.build().setCode(400).setMessage(message);
    }

    public static Result failure(String message, Object data) {
        return Result.build().setCode(400).setMessage(message).setData(data);
    }

}
