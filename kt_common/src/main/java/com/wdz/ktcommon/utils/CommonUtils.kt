package com.wdz.ktcommon.utils

import android.content.Context
import android.net.ConnectivityManager
import com.tencent.mmkv.MMKV
import com.wdz.ktcommon.response.LoginResponse

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
@Synchronized
fun setCookie(cookie: String?) {
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode("cookie", cookie)
}

/**
 * 读取cookie
 * @return
 */
@Synchronized
fun getCookie(): String? {
    val mmkv: MMKV = MMKV.defaultMMKV()
    return mmkv.decodeString("cookie", "")
}

fun isNetWorkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo?.isAvailable ?: false
}

/**
 * 存储loginResponse
 * @param loginResponse
 */
@Synchronized
fun setLoginUser(loginResponse: LoginResponse?) {
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode("loginResponse", loginResponse)
}

/**
 * 清空loginResponse
 */
@Synchronized
fun clearLoginUser() {
    val mmkv = MMKV.defaultMMKV()
    mmkv.remove("loginResponse")
}

/**
 * 读取loginResponse
 * @return
 */
@Synchronized
fun getLoginUser(): LoginResponse? {
    val mmkv = MMKV.defaultMMKV()
    return mmkv.decodeParcelable<LoginResponse>("loginResponse", LoginResponse::class.java)
}