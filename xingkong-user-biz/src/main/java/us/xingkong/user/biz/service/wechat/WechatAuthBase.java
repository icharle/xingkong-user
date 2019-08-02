package us.xingkong.user.biz.service.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import us.xingkong.user.api.enums.WechatEnum;
import us.xingkong.user.api.request.WechatAuthCallbackRequest;
import us.xingkong.user.api.request.WechatAuthRequest;
import us.xingkong.user.api.response.WechatUserInfoResponse;
import us.xingkong.user.api.utils.Response;
import us.xingkong.user.api.utils.Responses;
import us.xingkong.user.biz.entity.WechatAuthToken;
import us.xingkong.user.biz.entity.WechatIdAndSecret;
import us.xingkong.user.biz.utils.UrlBuilder;
import us.xingkong.user.biz.utils.ZfUtils;

import java.io.IOException;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 14:48
 */
public abstract class WechatAuthBase {

    private WechatEnum wechatEnum;
    private WechatIdAndSecret idAndSecret;

    public WechatAuthBase(WechatEnum wechatEnum, WechatIdAndSecret idAndSecret) {
        this.wechatEnum = wechatEnum;
        this.idAndSecret = idAndSecret;
    }

    public Response<String> authorizeUrl(WechatAuthRequest wechatAuthRequest) {
        String result = UrlBuilder.fromBaseUrl(wechatEnum.authorize())
                .buildWithParam("appid", idAndSecret.getAppid())
                .buildWithParam("redirect_uri", wechatAuthRequest.getRedirectUri())
                .buildWithParam("response_type", "code")
                .buildWithParam("scope", wechatAuthRequest.getScope())
                .buildWithParam("state", wechatAuthRequest.getState().concat("#wechat_redirect"))
                .build();
        return Responses.of(result);
    }

    public Response<WechatUserInfoResponse> getUserInfo(WechatAuthCallbackRequest wechatAuthCallbackRequest) {
        String tokenUrl = this.accessTokenUrl(wechatAuthCallbackRequest.getCode());
        WechatAuthToken authToken = this.getToken(tokenUrl);
        String userInfoUrl = this.userInfoUrl(authToken);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getToken = new HttpGet(userInfoUrl);
        try {
            HttpResponse response = client.execute(getToken);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                JSONObject object = JSONObject.parseObject(ZfUtils.toEncoding(httpEntity.getContent(), "UTF-8"));
                WechatUserInfoResponse buildResponse = WechatUserInfoResponse.builder()
                        .openId(authToken.getOpenId())
                        .unionid(object.getString("unionid"))
                        .city(object.getString("city"))
                        .country(object.getString("country"))
                        .nickname(object.getString("nickname"))
                        .headimgurl(object.getString("headimgurl"))
                        .province(object.getString("province"))
                        .sex(object.getInteger("sex"))
                        .build();
                Responses.of(buildResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Responses.fail(500, "未知错误");
    }

    protected WechatAuthToken getToken(String tokenUrl) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getToken = new HttpGet(tokenUrl);
        try {
            HttpResponse response = client.execute(getToken);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                JSONObject object = JSONObject.parseObject(ZfUtils.toEncoding(httpEntity.getContent(), "UTF-8"));
                return WechatAuthToken.builder()
                        .accessToken(object.getString("access_token"))
                        .refreshToken(object.getString("refresh_token"))
                        .openId(object.getString("openid"))
                        .unionId(object.getString("unionid"))
                        .expiresIn(object.getInteger("expires_in"))
                        .build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(wechatEnum.accessToken())
                .buildWithParam("appid", idAndSecret.getAppid())
                .buildWithParam("secret", idAndSecret.getSecret())
                .buildWithParam("code", code)
                .buildWithParam("grant_type", "authorization_code")
                .build();
    }

    protected String userInfoUrl(WechatAuthToken authToken) {
        return UrlBuilder.fromBaseUrl(wechatEnum.userInfo())
                .buildWithParam("access_token", authToken.getAccessToken())
                .buildWithParam("openid", authToken.getOpenId())
                .buildWithParam("lang", "zh_CN")
                .build();
    }

    protected String refreshTokenUrl(WechatAuthToken authToken) {
        return UrlBuilder.fromBaseUrl(wechatEnum.refreshToken())
                .buildWithParam("appid", idAndSecret.getAppid())
                .buildWithParam("refresh_token", authToken.getRefreshToken())
                .buildWithParam("grant_type", "refresh_token")
                .build();
    }


}
