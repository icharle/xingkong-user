package us.xingkong.user.api.remoteservice;

import us.xingkong.user.api.request.WechatAuthCallbackRequest;
import us.xingkong.user.api.request.WechatAuthRequest;
import us.xingkong.user.api.response.WechatUserInfoResponse;
import us.xingkong.user.api.utils.Response;

/**
 * 微信开放平台
 *
 * @Author: Icharle
 * @Date: 2019-08-01 11:57
 */
public interface WechatOpenAuthApi {

    /**
     * 返回授权页面URL
     *
     * @param wechatAuthRequest
     * @return
     */
    Response<String> authorizeUrl(WechatAuthRequest wechatAuthRequest);

    /**
     * 用户扫码确认授权登录返回用户信息
     *
     * @param wechatAuthCallbackRequest
     * @return
     */
    Response<WechatUserInfoResponse> getUserInfo(WechatAuthCallbackRequest wechatAuthCallbackRequest);

}
