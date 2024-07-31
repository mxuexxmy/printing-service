package xyz.mxue.printing.service.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "分类传输对象")
@Data
public class CategoriesNameDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "分类ID")
    private Long categoriesId;

    @ApiModelProperty(value = "分类名称")
    private String categoriesName;
}
