package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "月统计视图")
public class StatisticsDetailsMothVO {

    @ApiModelProperty(value = "月统计集合")
    private List<StatisticsDateVO> statisticsTimes;
}
