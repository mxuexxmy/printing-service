package xyz.mxue.printing.service.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "重置密码传输对象")
@Data
public class PasswordDTO implements Serializable {

    /**
     * 老密码
     */
    @ApiModelProperty(value = "老密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;

}
