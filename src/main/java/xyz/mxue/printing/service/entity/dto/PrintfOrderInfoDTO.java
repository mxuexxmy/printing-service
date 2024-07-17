package xyz.mxue.printing.service.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mxuexxmy
 * @date 3/23/2021$ 5:27 PM$
 */
@Data
public class PrintfOrderInfoDTO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 顾客姓名
     */
    private String userName;

    /**
     * 打印订单信息集合
     */
    private List<PrintfInfoDTO> printfInfos;

    /**
     * 打印人的电话
     */
    private String userPhone;

    /**
     * 打印人的QQ
     */
    private String userQq;

    /**
     * 打印人的微信
     */
    private String userWxchat;

    /**
     * 地点
     */
    private String address;

    /**
     * 备注
     */
    private String note;


}
