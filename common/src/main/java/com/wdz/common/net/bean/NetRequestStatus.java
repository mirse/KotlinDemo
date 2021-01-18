package com.wdz.common.net.bean;

import com.wdz.common.net.HttpRequestStatus;

/**
 * @Author dezhi.wang
 * @Date 2021/1/18 8:42
 */
public class NetRequestStatus<T> {
    public HttpRequestStatus requestStatus = HttpRequestStatus.NONE;
    public String errorMsg;
    public T response;

}
