package com.wdz.ktcommon.base

import android.net.ParseException
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import org.json.JSONException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 16:13

 */
open class BaseRepository {

    /**
     * 处理网络网络请求回调
     * @param resp BaseResp<T>
     * @return HttpResult<T>
     */
    fun <T : Any> executeResponse(resp: BaseResp<T>):HttpResult<T>{
        return if (resp.code == 0){
            if (resp.errorMsg.isBlank()){
                HttpResult.Success(resp.data)

            } else{
                HttpResult.Error(IOException(resp.errorMsg))
            }

        } else{
            HttpResult.Error(IOException(resp.errorMsg))
        }
    }

    /**
     * 根据e类型获取错误类型
     * @param e Exception
     * @return String
     */
    fun getErrorType(e:Exception): String {
        when(e){
            is ConnectException -> {
                return "连接服务器错误,请检查网络"
            }
            is HttpException -> {
                return e.localizedMessage
            }
//            is RuntimeException -> {
//
//            }
            is JsonParseException,is JSONException,is ParseException -> {
                return "服务器数据解析错误"
            }
            is IOException,is SocketException -> {
                return "连接服务器错误,请检查网络"
            }
            else -> {
                return "未知错误"
            }

        }
    }
}