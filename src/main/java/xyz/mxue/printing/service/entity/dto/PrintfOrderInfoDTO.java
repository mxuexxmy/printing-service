package xyz.mxue.printing.service.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@ApiModel(value = "打印订单信息视图")
@Data
public class PrintfOrderInfoDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 顾客姓名
     */
    @ApiModelProperty(value = "顾客姓名")
    private String userName;

    /**
     * 打印订单信息集合
     */
    @ApiModelProperty(value = "打印订单信息集合")
    private List<PrintfInfoDTO> printfInfos;

    /**
     * 打印人的电话
     */
    @ApiModelProperty(value = "打印人的电话")
    private String userPhone;

    /**
     * 打印人的QQ
     */
    @ApiModelProperty(value = "打印人的QQ")
    private String userQq;

    /**
     * 打印人的微信
     */
    @ApiModelProperty(value = "打印人的微信")
    private String userWxchat;

    /**
     * 地点
     */
    @ApiModelProperty(value = "地点")
    private String address;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;


}
