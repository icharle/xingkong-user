package us.xingkong.user.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 11:04
 */
@Getter
@AllArgsConstructor
public enum WechatEnum {

    /**
     * 微信开放平台
     */
    WECHAT_OPEN {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refreshToken() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    },

    /**
     * 微信公众平台
     */
    WECHAT_PUBLIC {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refreshToken() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    };

    public abstract String authorize();

    public abstract String accessToken();

    public abstract String userInfo();

    public abstract String refreshToken();

}
