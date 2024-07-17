package xyz.mxue.printing.service.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 */
@Data
public class MoneyAndSpendTypeDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 消费类型
     */
    private Integer spendType;

}
