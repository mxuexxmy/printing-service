package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "账单视图")
@Data
public class AccountVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private Double money;

    /**
     * 类别名
     */
    @ApiModelProperty(value = "类别名")
    private String categoriesName;

    /**
     * 消费类型
     */
    @ApiModelProperty(value = "消费类型")
    private Integer spendType;

    /**
     * 描述-说明
     */
    @ApiModelProperty(value = "描述-说明")
    private String description;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 消费类型
     */
    @ApiModelProperty(value = "消费类型")
    private String spendTypeName;

}
