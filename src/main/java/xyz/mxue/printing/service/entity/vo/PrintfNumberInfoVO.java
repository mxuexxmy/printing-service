package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "打印信息视图")
@Data
public class PrintfNumberInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 0是单面。1是双面
     */
    @ApiModelProperty(value = "0是单面。1是双面")
    private String singleDoubleSided;

    /**
     * 打印文件名
     */
    @ApiModelProperty(value = "打印文件名")
    private String fileName;


}
