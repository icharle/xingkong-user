package us.xingkong.user.biz.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import us.xingkong.common.utils.UUIDGenerator;
import us.xingkong.user.api.Response.VerificationCodeResponse;
import us.xingkong.user.api.enums.ZFanUrlEnum;
import us.xingkong.user.api.remoteservice.ZFanSystemApi;
import us.xingkong.user.api.utils.Response;
import us.xingkong.user.api.utils.Responses;
import us.xingkong.user.biz.utils.ZfUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Icharle
 * @Date: 2019-07-30 17:54
 */
@Service
@Slf4j
public class ZFanSystemApiImpl implements ZFanSystemApi {

    public Response<VerificationCodeResponse> verificationCode() {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uuid = UUIDGenerator.getUUID();
        //创建一个HttpContext对象，用来保存Cookie
        HttpClientContext httpClientContext = HttpClientContext.create();
        //教务系统验证码get请求方式
        HttpGet getCodeUrl = new HttpGet(ZFanUrlEnum.JW_CODE_URL.getUrl());
        try {
            // 执行并将结果存入httpClientContext中
            httpClient.execute(getCodeUrl, httpClientContext);
            // 得到cookie
            CookieStore cookieStore = httpClientContext.getCookieStore();
            log.error(String.valueOf(cookieStore));
            // 保存cookie
            ZfUtils.saveCookieStore(cookieStore, "/Applications/Java/xingkong-admin/cookie/" + uuid + ".txt");
            // 保存验证码
            File storeFile = new File("/Applications/Java/xingkong-admin/pic/" + uuid + ".png");
            FileOutputStream fos = new FileOutputStream(storeFile);
            httpClientContext.getResponse().getEntity().writeTo(fos);
            fos.close();

            VerificationCodeResponse verificationCodeResponse = new VerificationCodeResponse();
            verificationCodeResponse.setCodeUrl("/Applications/Java/xingkong-admin/pic/" + uuid + ".png");
            verificationCodeResponse.setSessionId(uuid);
            return Responses.of(verificationCodeResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Responses.fail(500, "错误");
    }


}
