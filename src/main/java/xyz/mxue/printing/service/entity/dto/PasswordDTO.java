package xyz.mxue.printing.service.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mxuexxmy
 * @date 3/25/2021$ 1:15 AM$
 */
@Data
public class PasswordDTO implements Serializable {

    /**
     * 老密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

}
