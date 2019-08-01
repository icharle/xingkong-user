package us.xingkong.user.api.request;

import lombok.Data;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 14:16
 */
@Data
public class WechatAuthCallbackRequest {

    /**
     * 回调url中的code参数
     */
    private String code;

    /**
     * 随机值 (前后两次对比  防止CSRF攻击)
     */
    private String state;

}
