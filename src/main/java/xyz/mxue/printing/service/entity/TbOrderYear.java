package xyz.mxue.printing.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 年记录
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbOrderYear implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 统计年
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy")
    private Date statsYear;

    /**
     * 统计年打印的份数
     */
    private Integer printfNumber;

    /**
     * 统计年费用
     */
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 日期，虚假字段
     */
    @TableField(exist = false)
    private String flagPermDate;


}
