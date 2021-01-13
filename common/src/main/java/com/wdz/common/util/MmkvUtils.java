package com.wdz.common.util;

import com.tencent.mmkv.MMKV;
import com.wdz.common.net.response.LoginResponse;

public class MmkvUtils {
    /*
    * 1、MMKV.defaultMMKV创建全局实例
    *
    * 2、区分不同业务存储，单独创建MMKV的实例使用MMKV.mmkvWithID
    * 如果业务需要多进程访问，加上标志位MMKV.MULTI_PROCESS_MODE
    *
    * 存储：mmkv.encode("key","object")
    * 读取：mmkv.decodeString("key")
    *
    */

    /**
     * 存储cooie
     * @param cookie
     */
    public synchronized static void setCookie(String cookie){
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("cookie",cookie);
    }

    /**
     * 读取cookie
     * @return
     */
    public synchronized static String getCookie(){
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeString("cookie","");
    }

    /**
     * 存储loginResponse
     * @param loginResponse
     */
    public synchronized static void setLoginUser(LoginResponse loginResponse){
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("loginResponse",loginResponse);
    }

    /**
     * 读取loginResponse
     * @return
     */
    public synchronized static LoginResponse getLoginUser(){
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeParcelable("loginResponse",LoginResponse.class);
    }



}
