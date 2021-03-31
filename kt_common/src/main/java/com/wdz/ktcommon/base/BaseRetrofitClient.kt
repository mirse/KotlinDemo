package com.wdz.ktcommon.base

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 11:37

 */
abstract class BaseRetrofitClient {
    companion object{
        private val TIME_OUT = 5;
    }
    /**
     * 初始化OkHttp相关
     * @return OkHttpClient
     */
    private fun getOkHttpClient():OkHttpClient{
        val builder = OkHttpClient.Builder() //设置连接超时时间
            .connectTimeout(
                TIME_OUT.toLong(),
                TimeUnit.SECONDS
            ) //设置读取超时时间
            .readTimeout(
                TIME_OUT.toLong(),
                TimeUnit.SECONDS
            ) //设置写入超时时间
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            //创建日志拦截器
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))


        handlerBuilder(builder)
        return builder.build()
    }

    protected abstract fun handlerBuilder(builder:OkHttpClient.Builder)

    fun <T> getService(service:Class<T> ,baseUrl:String): T {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build().create(service)
    }
}