package us.xingkong.user.api.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 12:04
 */
@Data
@Builder
public class WechatUserInfoResponse implements Serializable {

    private String openId;

    private String unionid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1为男性，2为女性
     */
    private int sex;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像url
     */
    private String headimgurl;

}
