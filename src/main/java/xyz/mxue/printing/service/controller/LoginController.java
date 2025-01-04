package xyz.mxue.printing.service.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.command.LoginCommand;
import xyz.mxue.printing.service.common.enums.CacheConstant;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.common.service.CacheService;
import xyz.mxue.printing.service.entity.TbUser;
import xyz.mxue.printing.service.entity.vo.CodeDataVO;
import xyz.mxue.printing.service.entity.vo.LoginProperties;
import xyz.mxue.printing.service.enums.LoginCodeEnum;
import xyz.mxue.printing.service.service.TbUserService;

import javax.annotation.Resource;
import java.util.Objects;


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

    @Resource
    private CacheService cacheService;

    @Autowired
    private CacheManager cacheManager;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<SaTokenInfo> login(@RequestBody LoginCommand command) {
        Cache cache = cacheManager.getCache(CacheConstant.LOGIN_CODE);
        if (cache == null) {
            return Result.fail("验证码不存在！");
        }
        if (StrUtil.isBlank(command.getUuid())) {
            return Result.fail("验证码缺失！");
        }
        Cache.ValueWrapper wrapper = cache.get(command.getUuid());
        if (wrapper == null) {
            return Result.fail("验证码已失效！");
        }
        String code = (String) wrapper.get();
        if (!Objects.equals(code, command.getCode())) {
            return Result.fail("验证码输入错误，请重新输入！");
        }
        TbUser user = userService.getByUsername(command.getUserPhone(), command.getPassword());
        if (user == null) {
            return Result.fail("手机号或者密码不正确!");
        }
        // 登录成功
        else {
            // 第1步，先登录上
            StpUtil.login(user.getId());
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Result.success("登录成功！", tokenInfo);
        }
    }

    @ApiOperation(value = "退出登录")
    @SaCheckLogin
    @GetMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("系统退出成功！");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @ApiOperation(value = "查询登录状态")
    @GetMapping("isLogin")
    @SaCheckLogin
    public Result<Boolean> isLogin() {
        return Result.success("当前会话是否登录：" + StpUtil.isLogin(), StpUtil.isLogin()) ;
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/code")
    public Result<CodeDataVO> getCode() {
        LoginProperties loginProperties = new LoginProperties();
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = "code-key-"+ IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if(captcha.getCharType()-1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")){
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保持到缓存
        cacheService.putValue(CacheConstant.LOGIN_CODE, uuid, captchaValue);

        CodeDataVO codeDataVO = new CodeDataVO();
        // 验证码信息
        codeDataVO.setImg(captcha.toBase64());
        codeDataVO.setUuid(uuid);
        return Result.success(codeDataVO);
    }

}
