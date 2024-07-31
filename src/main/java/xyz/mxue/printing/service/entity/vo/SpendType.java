package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "消费类型")
@Data
public class SpendType implements Serializable {

    @ApiModelProperty(value = "消费类型编码")
    private Integer spendType;

    @ApiModelProperty(value = "消费类型名称")
    private String spendTypeName;

}
