package xyz.mxue.printing.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 日记录
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@ApiModel(value = "日记录")
@Data
@EqualsAndHashCode(callSuper = false)
public class TbOrderDay implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
    private Date statsDay;

    /**
     * 每日打印份数
     */
    @ApiModelProperty(value = "每日打印份数")
    private Integer printfNumber;

    /**
     * 每日统计的费用
     */
    @ApiModelProperty(value = "每日统计的费用")
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 日期，虚假字段
     */
    @ApiModelProperty(value = "日期，虚假字段,前端使用")
    @TableField(exist = false)
    private String flagPermDate;

}
