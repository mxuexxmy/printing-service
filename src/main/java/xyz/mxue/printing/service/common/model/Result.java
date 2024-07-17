package xyz.mxue.printing.service.common.model;

import java.io.Serializable;

/**
 * 自定义返回结果
 * @author mxuexxmy
 * @date 12/6/2020$ 11:26 PM$
 */
public class Result implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    private String message;

    public static Result success() {
        return Result.createResult(STATUS_SUCCESS, "成功");
    }

    public static Result fail() {
        return Result.createResult(STATUS_FAIL, "失败");
    }

    public static Result success(String message) {
        return Result.createResult(STATUS_SUCCESS, message);
    }

    public static Result fail(String message) {
        return Result.createResult(STATUS_FAIL, message);
    }

    public static Result fail(int status, String message) {
        return Result.createResult(status, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static Result createResult(int status, String message) {
        Result Result = new Result();
        Result.setStatus(status);
        Result.setMessage(message);
        return Result;
    }
}
