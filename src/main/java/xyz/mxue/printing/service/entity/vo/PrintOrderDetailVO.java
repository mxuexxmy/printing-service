package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.mxue.printing.service.entity.TbPrintOrder;

import java.util.List;

@Data
@ApiModel(value = "打印订单详情视图")
public class PrintOrderDetailVO {

    @ApiModelProperty(value = "订单")
    private TbPrintOrder order;

    @ApiModelProperty(value = "份数详情")
    List<PrintfNumberInfoVO> printfInfos;
}
