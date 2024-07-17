package xyz.mxue.printing.service.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @date 3/23/2021$ 10:10 PM$
 */
@Data
public class IndexInfoVO {

    /**
     * 今日打印单数
     */
    private Long dayPrintfNumber;

    /**
     * 今日打印收入
     */
    private BigDecimal dayPrintfIncome;

    /**
     * 今日收入
     */
    private BigDecimal dayIncome;

    /**
     * 今日支出
     */
    private BigDecimal dayPayOut;
}
