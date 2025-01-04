package xyz.mxue.printing.service.entity.vo;

import lombok.Data;
import lombok.Getter;
import xyz.mxue.printing.service.enums.LoginCodeEnum;

@Data
public class LoginCode {

    /**
     * 验证码配置
     */
    @Getter
    private LoginCodeEnum codeType;
    /**
     * 验证码有效期 分钟
     */
    private Long expiration = 2L;
    /**
     * 验证码内容长度
     */
    private int length = 2;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
    /**
     * 验证码字体
     */
    private String fontName;
    /**
     * 字体大小
     */
    private int fontSize = 25;

    /**
     * 验证码前缀
     *
     * @return
     */
    private String codeKey;

}
