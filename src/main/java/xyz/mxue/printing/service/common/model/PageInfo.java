package xyz.mxue.printing.service.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页功能
 * @author mxuexxmy
 * @since 1.0.0
 */
@Data
@ApiModel(value = "分页对象")
public class PageInfo<T> implements Serializable {


    @ApiModelProperty(value = "第几页")
    private int draw;

    @ApiModelProperty(value = "总长度")
    private Long recordsTotal;

    @ApiModelProperty(value = "过滤后记录数")
    private Long recordsFiltered;

    @ApiModelProperty(value = "集合对象")
    private List<T> data;

    @ApiModelProperty(value = "错误信息")
    private String error;

}
