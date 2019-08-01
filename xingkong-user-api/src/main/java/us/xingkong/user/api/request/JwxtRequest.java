package us.xingkong.user.api.request;

import lombok.Data;

/**
 * @Author: Icharle
 * @Date: 2019-07-31 17:02
 */
@Data
public class JwxtRequest {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 密码
     */
    private String studentPw;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码对应cookie
     */
    private String sessionId;
}
