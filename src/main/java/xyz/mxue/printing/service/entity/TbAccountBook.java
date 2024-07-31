package xyz.mxue.printing.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  账本
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
@ApiModel(value = "账本")
@Data
@EqualsAndHashCode(callSuper = false)
public class TbAccountBook implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    /**
     * 类别名id
     */
    @ApiModelProperty(value = "类别名id")
    private Long categoriesId;

    /**
     * 消费类型
     */
    @ApiModelProperty(value = "消费类型")
    private Integer spendType;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 日期，虚假字段
     */
    @ApiModelProperty(value = "日期，虚假字段,前端展示")
    @TableField(exist = false)
    private String flagPermDate;



 }
