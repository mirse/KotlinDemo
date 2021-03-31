package com.wdz.ktcommon.http
///**
// * @Author dezhi.wang
// * @Date 2021/1/15 11:47
// */
//public class HttpRequestStatus<T> {
//    private T msg;
//    /**
//     * 可以请求（满足条件）
//     */
//    public static final HttpRequestStatus CAN_REQUEST = new HttpRequestStatus();
//    /**
//     * 不可以请求（不满足条件：未登录，未输入账号密码等等）
//     */
//    public static final HttpRequestStatus CANNOT_REQUEST = new HttpRequestStatus();
//    /**
//     * 正在请求
//     */
//    public static final HttpRequestStatus REQUESTING = new HttpRequestStatus();
//    /**
//     * 请求成功
//     */
//    public static final HttpRequestStatus REQUEST_SUCCESS = new HttpRequestStatus();
//    /**
//     * 请求失败
//     */
//    public static final HttpRequestStatus REQUEST_FAIL = new HttpRequestStatus();
//    /**
//     * 空项
//     */
//    public static final HttpRequestStatus NONE = new HttpRequestStatus();
//
//
//    private HttpRequestStatus(T msg){
//        this.msg = msg;
//    }
//
//    public HttpRequestStatus() {
//
//    }
//
//    public void setMsg(T msg) {
//        this.msg = msg;
//    }
//
//    public T getMsg() {
//        return msg;
//    }
//
//}
/**
 * @Author dezhi.wang
 * @Date 2021/1/15 11:47
 */
enum class HttpRequestStatus {
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
    NONE;

    /**
     * 请求所携带的消息
     */
    var msg: Any? = null
    var status: Any? = null


    fun setMsg(msg: Any?): HttpRequestStatus {
        this.msg = msg
        return this
    }

    fun setStatus(status: Any?): HttpRequestStatus {
        this.status = status
        return this
    }

    override fun toString(): String {
        return "HttpRequestStatus{" +
                "msg=" + msg +
                ", status=" + status +
                '}'
    }
}