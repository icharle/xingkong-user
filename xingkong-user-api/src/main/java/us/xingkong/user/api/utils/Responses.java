package us.xingkong.user.api.utils;

/**
 * @Author: Icharle
 * @Date: 2019-07-31 15:15
 */
public abstract class Responses {

    public static <T> Response<T> of(T data) {
        Response<T> response = new Response<T>();
        response.setData(data);
        response.setCode(200);
        response.setMsg("请求成功");
        return response;
    }

    public static <T> Response<T> of(T data, String msg) {
        Response<T> response = of(data);
        response.setMsg(msg);
        return response;
    }

    public static <T> Response<T> fail(int code, String msg) {
        Response<T> response = new Response<T>();
        response.setData(null);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
