package com.wdz.ktcommon.http.interceptor

import android.util.Log
import com.wdz.ktcommon.utils.getCookie
import okhttp3.Interceptor
import okhttp3.Response

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:21

 */
class AddCookiesInterceptor:Interceptor {
    private val TAG = this::class.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        Log.i(TAG, "intercept: " + getCookie())
        builder.addHeader("Cookie", getCookie())
        return chain.proceed(builder.build())
    }
}