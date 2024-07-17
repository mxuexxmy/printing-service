package xyz.mxue.printing.service.common.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 12/7/2020$ 10:49 PM$
 */
@Getter
public enum OrderStatusEnum implements Serializable {
    /**
     * 1-已完成
     */
    UNDONE(1, "否"),
    /**
     * 2-未完成
     */
    COMPLETE(2, "是");

    private final Integer value;
    private final String desc;

    OrderStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
