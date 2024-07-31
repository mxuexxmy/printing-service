package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "年统计视图")
public class StatisticsDetailsYearVO {

    @ApiModelProperty(value = "年统计集合")
    private List<StatisticsDateVO> statisticsTimes;
}
