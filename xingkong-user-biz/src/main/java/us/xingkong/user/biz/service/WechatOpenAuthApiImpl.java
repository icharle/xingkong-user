package us.xingkong.user.biz.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import us.xingkong.user.api.enums.WechatEnum;
import us.xingkong.user.api.remoteservice.WechatOpenAuthApi;
import us.xingkong.user.biz.entity.WechatIdAndSecret;
import us.xingkong.user.biz.service.wechat.WechatAuthBase;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 14:43
 */
@Data
@Service
@PropertySource(value={"classpath:classpath:config/wechat/wechat.properties"})
public class WechatOpenAuthApiImpl extends WechatAuthBase implements WechatOpenAuthApi {

    @SuppressWarnings("static-access")
    @Value("${wechat.open.appid}")
    private static String appid;

    @SuppressWarnings("static-access")
    @Value("${wechat.open.secret}")
    private static String secret;

    public WechatOpenAuthApiImpl() {
        super(WechatEnum.WECHAT_OPEN, new WechatIdAndSecret(appid, secret));
    }

}
