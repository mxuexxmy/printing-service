package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "验证码信息")
public class CodeDataVO {

    @ApiModelProperty(value = "照片")
    private String img;

    @ApiModelProperty(value = "关键字")
    private String uuid;
}
