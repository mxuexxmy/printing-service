package xyz.mxue.printing.service.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @date 3/23/2021$ 5:31 PM$
 */
@Data
public class PrintfInfoDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 单双面
     */
    private Integer singleDoubleSided;

    /**
     * 打印页数
     */
    private Integer pagesNumber;

    /**
     * 打印份数
     */
    private Integer printfNumber;

    /**
     * 打印一张的价格
     */
    private BigDecimal amount;

    /**
     * 打印文件名
     */
    private String fileName;

}
