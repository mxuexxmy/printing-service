package xyz.mxue.printing.service.common.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @since 1.0.0
 */
@Getter
public enum UserTypeEnum implements Serializable {
    /**
     * 1-系统管理员
     */
    SYSTEM(1, "系统管理员"),
    /**
     * 2-管理
     */
    MANAGEMENT(2, "管理"),
    /**
     * 3-用户
     */
    USER(3, "用户");

    private final Integer value;
    private final String desc;

    UserTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
