package xyz.mxue.printing.service.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @date 3/24/2021$ 4:09 AM$
 */
@Data
public class PrintfNumberInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 打印一张的价格
     */
    private BigDecimal amount;

    /**
     * 打印的钱
     */
    private BigDecimal printfMoney;

    /**
     * 打印的页数
     */
    private Integer pagesNumber;

    /**
     * 打印的份数
     */
    private Integer printfNumber;

    /**
     * 0是单面。1是双面
     */
    private String singleDoubleSided;

    /**
     * 打印文件名
     */
    private String fileName;


}
