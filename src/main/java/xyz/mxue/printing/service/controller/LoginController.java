package xyz.mxue.printing.service.controller;

import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.enums.ConstantUtils;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbUser;
import xyz.mxue.printing.service.service.TbUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author mxuexxmy
 * @date 12/8/2020$ 12:47 AM$
 */
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private TbUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody TbUser tbUser, HttpServletRequest httpServletRequest) {
        TbUser user = userService.getByUsername(tbUser.getUserPhone(), tbUser.getPassword());
        if (user == null) {
            return Result.fail("手机号或者密码不正确");
        }
        // 登录成功
        else {
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            return Result.success("登录成功！");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return Result.success("系统退出成功！");
    }

}
