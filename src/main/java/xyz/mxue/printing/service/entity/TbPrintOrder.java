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
 * 打印订单
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-06
 */
@ApiModel(value = "打印订单")
@Data
@EqualsAndHashCode(callSuper = false)
public class TbPrintOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 打印人的名字
     */
    @ApiModelProperty(value = "打印人的名字")
    private String userName;

    /**
     * 打印人的QQ
     */
    @ApiModelProperty(value = "打印人的QQ")
    private String userQq;

    /**
     * 打印人的微信
     */
    @ApiModelProperty(value = "打印人的微信")
    private String userWxchat;

    /**
     * 打印人的电话
     */
    @ApiModelProperty(value = "打印人的电话")
    private String userPhone;

    /**
     * 总的价格
     */
    @ApiModelProperty(value = "总的价格")
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    /**
     * 地点
     */
    @ApiModelProperty(value = "地点")
    private String address;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
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
    @ApiModelProperty(value = "日期，虚假字段,前端使用")
    @TableField(exist = false)
    private String flagPermDate;


}
