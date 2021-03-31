package com.wdz.ktcommon.http.repository



import android.net.ParseException
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.wdz.ktcommon.base.BaseRepository
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.RetrofitManager
import com.wdz.ktcommon.http.response.MainListResponse
import org.json.JSONException
import java.io.IOException
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.SocketException

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 15:12

 */
class NetRepository: BaseRepository() {
    suspend fun getArticle(page: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.getArticle(page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    suspend fun collectArticleIn(id: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.collectArticleIn(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    suspend fun uncollect(id: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.uncollect(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }




}