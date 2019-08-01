package us.xingkong.user.biz.tansformers;

import com.google.common.base.Function;
import org.springframework.stereotype.Component;
import us.xingkong.user.api.response.WechatUserInfoResponse;

import javax.annotation.Nullable;

/**
 * @Author: Icharle
 * @Date: 2019-08-01 19:13
 */
@Component
public class WechatUserInfoResponseTansformers implements Function<Object, WechatUserInfoResponse> {
    @Nullable
    public WechatUserInfoResponse apply(@Nullable Object o) {
        return null;
    }
}
