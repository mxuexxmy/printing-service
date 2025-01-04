package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录VO")
public class LoginVO {

    @ApiModelProperty(value = "token")
    private String token;

}
