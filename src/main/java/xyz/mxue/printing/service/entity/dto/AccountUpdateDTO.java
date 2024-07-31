package xyz.mxue.printing.service.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.mxue.printing.service.entity.vo.SpendType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "账本更新传输对象")
@Data
public class AccountUpdateDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    /**
     * 类别列表
     */
    @ApiModelProperty(value = "类别列表")
    private List<CategoriesNameDTO> categoriesName;

    /**
     * 消费类型列表
     */
    @ApiModelProperty(value = "消费类型列表")
    private List<SpendType> spendTypes;

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

}
