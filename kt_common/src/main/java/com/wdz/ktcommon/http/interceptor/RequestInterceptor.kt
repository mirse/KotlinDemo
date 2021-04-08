package com.wdz.ktcommon.http.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:23

 */
class RequestInterceptor(private val isNetWorkAvailable:Boolean):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (isNetWorkAvailable) {
            //网络可用，根据缓存时间判断是否使用缓存
//            val cacheControl = CacheControl.Builder() //超过缓存有效时间后，可以继续使用旧缓存的时间
//                .maxStale(15, TimeUnit.MINUTES) //缓存的有限时间
//                .maxAge(15, TimeUnit.MINUTES).build()
//            builder.cacheControl(cacheControl)
        } else {
            //网络不可用，直接使用cache
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }



        return chain.proceed(builder.build())
    }
}