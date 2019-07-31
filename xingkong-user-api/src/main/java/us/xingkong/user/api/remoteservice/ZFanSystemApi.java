package us.xingkong.user.api.remoteservice;

import us.xingkong.user.api.Response.VerificationCodeResponse;
import us.xingkong.user.api.utils.Response;


/**
 * @Author: Icharle
 * @Date: 2019-07-30 17:47
 */
public interface ZFanSystemApi {

    Response<VerificationCodeResponse> verificationCode();

}
