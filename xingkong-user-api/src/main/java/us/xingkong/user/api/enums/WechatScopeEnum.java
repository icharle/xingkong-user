package us.xingkong.user.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 14:24
 */
@Getter
@AllArgsConstructor
public enum WechatScopeEnum {

    SNSAPI_BASE("snsapi_base", "微信公众平台静默登录方式"),

    SNAAPI_USERINFO("snsapi_userinfo", "微信公众平台弹出授权方式"),

    SNSAPI_LOGIN("snsapi_login", "微信开放平台扫码登录方式");

    private String scope;
    private String desc;

}
