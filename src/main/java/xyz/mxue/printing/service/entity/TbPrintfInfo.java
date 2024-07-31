package xyz.mxue.printing.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 打印信息
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-23
 */
@ApiModel(value = "打印信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class TbPrintfInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 打印订单ID
     */
    @ApiModelProperty(value = "打印订单ID")
    private Long orderId;

    /**
     * 打印一张的价格
     */
    @ApiModelProperty(value = "打印一张的价格")
    private BigDecimal amount;

    /**
     * 打印的钱
     */
    @ApiModelProperty(value = "打印的钱")
    private BigDecimal printfMoney;

    /**
     * 打印的页数
     */
    @ApiModelProperty(value = "打印的页数")
    private Integer pagesNumber;

    /**
     * 打印的份数
     */
    @ApiModelProperty(value = "打印的份数")
    private Integer printfNumber;

    /**
     * 打印文件名
     */
    @ApiModelProperty(value = "打印文件名")
    private String fileName;

    /**
     * 0是单面。1是双面
     */
    @ApiModelProperty(value = "0是单面。1是双面")
    private Integer singleDoubleSided;

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

}
