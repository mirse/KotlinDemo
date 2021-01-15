package com.wdz.common.net;

/**
 * @Author dezhi.wang
 * @Date 2021/1/15 11:47
 */
public enum HttpRequestStatus {
    /**
     * 可以请求（满足条件）
     */
    CAN_REQUEST,
    /**
     * 不可以请求（不满足条件：未登录，未输入账号密码等等）
     */
    CANNOT_REQUEST,
    /**
     * 正在请求
     */
    REQUESTING,
    /**
     * 请求成功
     */
    REQUEST_SUCCESS,
    /**
     * 请求失败
     */
    REQUEST_FAIL,
    /**
     * 空项
     */
    NONE
}
