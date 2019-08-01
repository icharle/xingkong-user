package us.xingkong.user.api.remoteservice;

import us.xingkong.user.api.request.JwxtRequest;
import us.xingkong.user.api.response.VerificationCodeResponse;
import us.xingkong.user.api.utils.Response;


/**
 * @Author: Icharle
 * @Date: 2019-07-30 17:47
 */
public interface ZFanSystemApi {

    Response<VerificationCodeResponse> verificationCode();

    Response<Boolean> jwxtLogin(JwxtRequest jwxtRequest);

}
