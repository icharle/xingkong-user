package us.xingkong.user.api.request;

import lombok.Data;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 14:11
 */
@Data
public class WechatAuthRequest {

    /**
     * 回调URL
     */
    private String redirectUri;

    /**
     * 随机值 (防止CSRF攻击)
     */
    private String state;

    /**
     * 场景值
     * us.xingkong.user.api.enums.WechatScopeEnum
     */
    private String scope;

}
