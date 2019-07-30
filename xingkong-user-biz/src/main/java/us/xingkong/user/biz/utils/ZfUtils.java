package us.xingkong.user.biz.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Icharle
 * @Date: 2019-07-30 15:29
 */
public class ZfUtils {

    /**
     * 字节流转成字符串(转换编码)
     *
     * @param is
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String toEncoding(InputStream is, String encoding) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        is.close();
        return new String(bos.toByteArray(), encoding);
    }

    /**
     * 获取到__VIEWSTATE值
     *
     * @param url
     * @param cookie
     * @param referer
     * @return
     */
    public static String getViewState(String url, String cookie, String referer) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getViewState = new GetMethod(url);
        getViewState.setRequestHeader("Cookie", cookie);
        getViewState.setRequestHeader("Referer", referer);

        httpClient.executeMethod(getViewState);
        String s = new String(getViewState.getResponseBody(), "GB2312");
        String viewState = Jsoup.parse(s).select("input[name=__VIEWSTATE]")
                .val();
        return viewState;
    }

}
