package us.xingkong.user.biz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 16:26
 */
@Data
@AllArgsConstructor
public class WechatIdAndSecret {

    private String appid;

    private String secret;
}
