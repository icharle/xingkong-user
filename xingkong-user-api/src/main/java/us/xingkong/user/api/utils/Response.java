package us.xingkong.user.api.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Icharle
 * @Date: 2019-07-31 14:45
 */
@Data
public class Response<T> implements Serializable {

    private int code;

    private T data;

    private String msg;
}
