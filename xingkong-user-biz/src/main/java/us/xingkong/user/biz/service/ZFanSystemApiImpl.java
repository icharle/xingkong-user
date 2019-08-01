package us.xingkong.user.biz.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import us.xingkong.common.utils.UUIDGenerator;
import us.xingkong.user.api.request.JwxtRequest;
import us.xingkong.user.api.response.VerificationCodeResponse;
import us.xingkong.user.api.enums.ZFanUrlEnum;
import us.xingkong.user.api.remoteservice.ZFanSystemApi;
import us.xingkong.user.api.utils.Response;
import us.xingkong.user.api.utils.Responses;
import us.xingkong.user.biz.utils.ZfUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            ZfUtils.saveCookieStore(cookieStore, ZFanUrlEnum.JW_COOKIE_SAVE + uuid + ".txt");
            // 保存验证码
            File storeFile = new File(ZFanUrlEnum.JW_CODE_SAVE + uuid + ".png");
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

    public Response<Boolean> jwxtLogin(JwxtRequest jwxtRequest) {
        try {
            String __VIEWSTATE = ZfUtils.getViewState(ZFanUrlEnum.JW_LOGIN_URL.getUrl(), "", "");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost loginClient = new HttpPost(ZFanUrlEnum.JW_LOGIN_URL.getUrl());
            loginClient.setHeader("Cookie", ZfUtils.readCookieStore(ZFanUrlEnum.JW_COOKIE_SAVE + jwxtRequest.getSessionId() + ".txt"));

            //设置登录时需要的信息，用户名和密码
            List<NameValuePair> nameValuePairLogin = new ArrayList<NameValuePair>();
            nameValuePairLogin
                    .add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
            nameValuePairLogin
                    .add(new BasicNameValuePair("txtUserName", jwxtRequest.getStudentId()));
            nameValuePairLogin.add(new BasicNameValuePair("TextBox2", jwxtRequest.getStudentPw()));
            nameValuePairLogin.add(new BasicNameValuePair("txtSecretCode", jwxtRequest.getCode()));
            nameValuePairLogin.add(new BasicNameValuePair("RadioButtonList1",
                    "学生"));
            nameValuePairLogin.add(new BasicNameValuePair("Button1", ""));
            nameValuePairLogin.add(new BasicNameValuePair("lbLanguage", ""));
            nameValuePairLogin.add(new BasicNameValuePair("hidPdrs", ""));
            nameValuePairLogin.add(new BasicNameValuePair("hidsc", ""));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                    nameValuePairLogin, "GB2312");
            loginClient.setEntity(entity);
            HttpResponse responseLogin = httpClient.execute(loginClient);
            String html = ZfUtils.toEncoding(responseLogin.getEntity().getContent(), "GB2312");
            if (responseLogin.getStatusLine().getStatusCode() == 302) {
                // 登录成功后续操作
                Responses.of(true);
            } else {
                if (html.contains("验证码不正确")) {
                    log.error("验证码不正确");
                    Responses.of(false, "验证码错误");
                }
                if (html.contains("密码错误")) {
                    log.error("密码错误");
                    Responses.of(false, "密码错误");
                }
                if (html.contains("用户名不存在")) {
                    log.error("用户名不存在或未按照要求参加教学活动");
                    Responses.of(false, "用户名不存在或未按照要求参加教学活动");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Responses.of(false, "未知错误");
    }


}
