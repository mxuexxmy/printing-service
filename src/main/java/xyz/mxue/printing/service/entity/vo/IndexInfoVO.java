package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @since 1.0
 */
@Data
@ApiModel(value = "首页信息对象")
public class IndexInfoVO {

    /**
     * 今日打印单数
     */
    @ApiModelProperty(value = "今日打印单数")
    private Long dayPrintfNumber;

    /**
     * 今日打印收入
     */
    @ApiModelProperty(value = "今日打印收入")
    private BigDecimal dayPrintfIncome;

    /**
     * 今日收入
     */
    @ApiModelProperty(value = "今日收入")
    private BigDecimal dayIncome;

    /**
     * 今日支出
     */
    @ApiModelProperty(value = "今日支出")
    private BigDecimal dayPayOut;
}
