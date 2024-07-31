package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.mxue.printing.service.entity.TbUser;

@Data
@ApiModel(value = "用户视图")
public class UserVO {

    @ApiModelProperty(value = "用户信息")
    private TbUser tbUser;
}
