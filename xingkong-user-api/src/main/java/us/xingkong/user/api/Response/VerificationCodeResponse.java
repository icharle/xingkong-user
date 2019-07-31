package us.xingkong.user.api.Response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Icharle
 * @Date: 2019-07-31 14:27
 */
@Data
public class VerificationCodeResponse implements Serializable {

    // cookie对应的
    private String sessionId;

    // 返回验证码链接
    private String codeUrl;
}
