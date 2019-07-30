package us.xingkong.user.biz.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import us.xingkong.user.api.enums.ZFanUrlEnum;
import us.xingkong.user.api.remoteservice.ZFanSystemApi;

import java.io.IOException;

/**
 * @Author: Icharle
 * @Date: 2019-07-30 17:54
 */
public class ZFanSystemApiImpl implements ZFanSystemApi {

    public String VerificationCode() {

        HttpClient client = new HttpClient();

        GetMethod getMethod = new GetMethod(ZFanUrlEnum.JW_CODE_URL.getUrl());

        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            client.executeMethod(getMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
