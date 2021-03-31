package com.wdz.ktcommon.http

import android.content.Context
import com.wdz.ktcommon.MyApplication
import com.wdz.ktcommon.base.BaseRetrofitClient
import com.wdz.ktcommon.http.coroutines.NetService
import com.wdz.ktcommon.http.interceptor.AddCookiesInterceptor
import com.wdz.ktcommon.http.interceptor.NetCacheInterceptor
import com.wdz.ktcommon.http.interceptor.ReceivedCookiesInterceptor
import com.wdz.ktcommon.http.interceptor.RequestInterceptor
import com.wdz.ktcommon.utils.isNetWorkAvailable
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 11:55

 */
object RetrofitManager: BaseRetrofitClient() {
    private val BasePath = "https://www.wanandroid.com"

    val service by lazy { getService(NetService::class.java,
        BasePath
    ) }
    override fun handlerBuilder(builder: OkHttpClient.Builder) {
        // 缓存目录
        val file = File(MyApplication.context.cacheDir, "kotlin_cache")
        // 缓存大小
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(file, cacheSize)
       // 配置缓存
        builder.cache(cache)
        builder
            //设置request和response Cache
            .addInterceptor(RequestInterceptor(isNetWorkAvailable(MyApplication.context)))
            .addInterceptor(NetCacheInterceptor())
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
    }
}