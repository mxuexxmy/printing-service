package xyz.mxue.printing.service.entity.dto;

import lombok.Data;
import xyz.mxue.printing.service.entity.vo.SpendType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author mxuexxmy
 * @date 3/16/2021$ 11:27 AM$
 */
@Data
public class AccountUpdateDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Long id;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 类别列表
     */
    private List<CategoriesNameDTO> categoriesName;

    /**
     * 消费类型列表
     */
    private List<SpendType> spendTypes;

    /**
     * 描述-说明
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updateTime;

}
