package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbUser;
import xyz.mxue.printing.service.service.TbUserService;

import javax.annotation.Resource;


/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@Api(tags = "登录管理")
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private TbUserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody TbUser tbUser) {
        TbUser user = userService.getByUsername(tbUser.getUserPhone(), tbUser.getPassword());
        if (user == null) {
            return Result.fail("手机号或者密码不正确");
        }
        // 登录成功
        else {
            StpUtil.login(user.getId());
            return Result.success("登录成功！");
        }
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("系统退出成功！");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @ApiOperation(value = "查询登录状态")
    @GetMapping("isLogin")
    public Result<Boolean> isLogin() {
        return Result.success("当前会话是否登录：" + StpUtil.isLogin(), StpUtil.isLogin()) ;
    }

}
