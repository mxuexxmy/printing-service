package xyz.mxue.printing.service.common.config;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.mxue.printing.service.common.model.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {

     /**
     * 拦截 Sa-Token 的 NotLoginException (即 @SaCheckLogin 未登录时抛出的异常)
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<?> handleNotLoginException(NotLoginException e) {
        // 这里可以根据 e.getType() 返回不同信息，也可以简单统一处理
        String message = parseNotLoginExceptionMessage(e);

        // 假设返回 401 作为“未登录”的状态码，也可根据需要使用别的数字
        return Result.fail(401, message);
    }

    /**
     * 可以通过 e.getType() 判断具体未登录的场景，从而定制不同的提示
     */
    private String parseNotLoginExceptionMessage(NotLoginException e) {
        // 获取异常类型
        String type = e.getType();
        switch (type) {
            case NotLoginException.NOT_TOKEN:
                return "未提供 Token，请先登录";
            case NotLoginException.INVALID_TOKEN:
                return "Token 无效或格式错误";
            case NotLoginException.TOKEN_TIMEOUT:
                return "登录已过期，请重新登录";
            case NotLoginException.BE_REPLACED:
                return "账号已在其它设备登录，本次登录已失效";
            case NotLoginException.KICK_OUT:
                return "账号已被踢下线";
            default:
                return "当前会话未登录，请先登录";
        }
    }

    /**
     * 其他异常的统一拦截也可以放在这个类里
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        // 记录日志，返回一个通用错误响应
        // log.error("系统异常: ", e);
        return Result.fail(500, "系统异常，请联系管理员");
    }
}
