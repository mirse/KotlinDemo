package com.wdz.ktcommon.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:21

 */
class NetCacheInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val maxStale = 60 * 30.toLong()
        //设置响应的缓存事件为30 minutes
        //设置响应的缓存事件为30 minutes
        response = response.newBuilder() //移除pragma,因为pragma也是控制缓存的一个消息头属性
            .removeHeader("Pragma")
            .header("Cache-Control", "max-age=$maxStale")
            .build()

        return response
    }
}