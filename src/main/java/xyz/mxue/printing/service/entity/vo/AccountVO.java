package xyz.mxue.printing.service.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mxuexxmy
 * @date 3/15/2021$ 11:50 AM$
 */
@Data
public class AccountVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 金额
     */
    private Double money;

    /**
     * 类别名
     */
    private String categoriesName;

    /**
     * 消费类型
     */
    private Integer spendType;

    /**
     * 描述-说明
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 消费类型
     */
    private String spendTypeName;

}
