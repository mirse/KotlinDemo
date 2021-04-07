package com.wdz.ktcommon.http.interceptor

import android.util.Log
import com.wdz.ktcommon.utils.setCookie
import okhttp3.Interceptor
import okhttp3.Response

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:22

 */
class ReceivedCookiesInterceptor:Interceptor {
    private val TAG = this::class.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.headers("Set-Cookie").isEmpty()) {
            val stringBuilder = StringBuilder()
            for (header in response.headers("Set-Cookie")) {
                stringBuilder.append(header)
                Log.i(TAG, "intercept: $header")
                if (header.contains("JESSIONID")) {
                    val cookie = header.substring(header.indexOf("JESSIONID"), header.indexOf(";"))
                    //MmkvUtils.setCookie(cookie);
                }
            }
            setCookie(stringBuilder.toString())
            Log.i(TAG, "stringBuilder: $stringBuilder")
        }
        return response
    }
}