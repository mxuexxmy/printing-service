package xyz.mxue.printing.service.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 自定义返回结果
 * @author mxuexxmy
 * @since 1
 */
@Setter
@Getter
@ApiModel(value = "结果返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    @ApiModelProperty(value = "状态值, 200 成功，500 失败")
    private int status;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;


    public static <T> Result<T> success() {
        return Result.createResult(STATUS_SUCCESS, "成功");
    }

    public static <T> Result<T> fail() {
        return Result.createResult(STATUS_FAIL, "失败");
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = Result.createResult(STATUS_SUCCESS, "成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = Result.createResult(STATUS_SUCCESS, message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = Result.createResult(STATUS_FAIL, "失败");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String message, T data) {
        Result<T> result = Result.createResult(STATUS_FAIL, message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message) {
        return Result.createResult(STATUS_SUCCESS, message);
    }

    public static <T> Result<T> fail(String message) {
        return Result.createResult(STATUS_FAIL, message);
    }

    public static <T> Result<T> fail(int status, String message) {
        return Result.createResult(status, message);
    }

    private static <T> Result<T> createResult(int status, String message) {
        Result<T> Result = new Result<>();
        Result.setStatus(status);
        Result.setMessage(message);
        return Result;
    }

}
