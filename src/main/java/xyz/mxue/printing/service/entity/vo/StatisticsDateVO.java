package xyz.mxue.printing.service.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.mxue.printing.service.entity.dto.CategoriesDetailsDTO;

import java.util.Date;
import java.util.List;

/**
 * @author mxuexxmy
 */
@ApiModel(value = "统计时间视图")
@Data
public class StatisticsDateVO {

    /**
     * 统计时间
     */
    @ApiModelProperty(value = "统计时间")
    private Date statisticsTime;

    /**
     * 时间展示
     */
    @ApiModelProperty(value = "时间展示")
    private String timeString;

    /**
     * 类别详情
     */
    @ApiModelProperty(value = "类别详情")
    private List<CategoriesDetailsDTO> categoriesDetails;

}
