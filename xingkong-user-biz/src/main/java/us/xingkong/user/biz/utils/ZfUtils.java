package us.xingkong.user.biz.utils;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
     * 保存cookie
     *
     * @param cookieStore cookie
     * @param savePath    文件路径
     * @throws IOException
     */
    public static void saveCookieStore(CookieStore cookieStore, String savePath) throws IOException {
        FileOutputStream fs = new FileOutputStream(savePath);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(cookieStore);
        os.close();
    }

    /**
     * 读取cookie
     *
     * @param savePath 文件路径
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static String readCookieStore(String savePath) throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(savePath);
        ObjectInputStream ois = new ObjectInputStream(fs);
        CookieStore cookieStore = (CookieStore) ois.readObject();
        ois.close();
        return String.valueOf(cookieStore);
    }

    /**
     * 获取__VIEWSTATE
     *
     * @param url
     * @param cookie
     * @param referer
     * @return
     * @throws IOException
     */
    public static String getViewState(String url, String cookie, String referer) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet getViewState = new HttpGet(url);
        getViewState.setHeader("Cookie", cookie);
        getViewState.setHeader("Referer", referer);
        InputStream is = httpClient.execute(getViewState).getEntity().getContent();
        return toEncoding(is, "GB2312");
    }


}
