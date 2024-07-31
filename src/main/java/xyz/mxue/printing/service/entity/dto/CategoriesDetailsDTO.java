package xyz.mxue.printing.service.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 */
@ApiModel(value = "分类统计传输对象")
@Data
public class CategoriesDetailsDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 类别名
     */
    @ApiModelProperty(value = "分类名称")
    private String categoriesName;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    /**
     * 消费类型
     */
    @ApiModelProperty(value = "消费类型")
    private Integer spendType;

}
