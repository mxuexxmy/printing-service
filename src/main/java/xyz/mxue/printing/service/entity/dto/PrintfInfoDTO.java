package xyz.mxue.printing.service.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "打印信息传输对象")
@Data
public class PrintfInfoDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 单双面
     */
    @ApiModelProperty(value = "单双面")
    private Integer singleDoubleSided;

    /**
     * 打印页数
     */
    @ApiModelProperty(value = "打印页数")
    private Integer pagesNumber;

    /**
     * 打印份数
     */
    @ApiModelProperty(value = "打印份数")
    private Integer printfNumber;

    /**
     * 打印一张的价格
     */
    @ApiModelProperty(value = "打印一张的价格")
    private BigDecimal amount;

    /**
     * 打印文件名
     */
    @ApiModelProperty(value = "打印文件名")
    private String fileName;

}
