package xyz.mxue.printing.service.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbUser;
import xyz.mxue.printing.service.entity.dto.PasswordDTO;
import xyz.mxue.printing.service.entity.vo.UserVO;
import xyz.mxue.printing.service.service.TbUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-08
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/printing/tb-user")
public class TbUserController {

    @Resource
    private TbUserService userService;

    @GetMapping("profile")
    public Result<UserVO> profile(HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        UserVO userVO = new UserVO();
        userVO.setTbUser(tbUser);
        return Result.success(userVO);
    }

    @PostMapping("update")
    public Result<Boolean> profileUpdate(@RequestBody TbUser tbUser, HttpServletRequest request) {
        TbUser tbUser1 = (TbUser) request.getSession().getAttribute("user");
        tbUser1.setUserName(tbUser.getUserName());
        tbUser1.setAddress(tbUser.getAddress());
        tbUser1.setUserPhone(tbUser.getUserPhone());
        boolean b = userService.updateById(tbUser1);
        return b ? Result.success("基本信息修改成功！") : Result.fail("基本信息修改失败");
    }

    /**
     * 修改个人密码
     *
     * @param passwordDTO
     * @param request
     * @return
     */
    @ApiOperation(value = "修改个人密码")
    @PostMapping("/password")
    public Result<Boolean> updatePassword(@RequestBody PasswordDTO passwordDTO, HttpServletRequest request) {
        System.out.println(passwordDTO);
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        // 查询原密码是否符合
        if (tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(passwordDTO.getOldPassword().getBytes()))) {
            tbUser.setPassword(DigestUtils.md5DigestAsHex(passwordDTO.getNewPassword().getBytes()));
            userService.updateById(tbUser);
            request.getSession().invalidate();
          return Result.success("密码修改成功，正在退出！");
        }
        return Result.fail("原密码错误，请重新输入");
    }

}

