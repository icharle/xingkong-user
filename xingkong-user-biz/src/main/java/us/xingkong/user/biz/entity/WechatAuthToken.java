package us.xingkong.user.biz.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 17:44
 */
@Data
@Builder
public class WechatAuthToken {

    private String accessToken;

    private int expiresIn;

    private String refreshToken;

    private String openId;

    private String unionId;
}
