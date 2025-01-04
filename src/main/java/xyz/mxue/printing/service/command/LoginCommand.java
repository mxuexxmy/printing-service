package xyz.mxue.printing.service.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "登录命令")
@Data
public class LoginCommand {

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "密钥")
    private String uuid;

}
