package com.wdz.ktcommon.http.repository



import android.net.ParseException
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.wdz.ktcommon.base.BaseRepository
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.RetrofitManager
import com.wdz.ktcommon.http.response.CollectArticleResponse
import com.wdz.ktcommon.http.response.HotKeyResponse
import com.wdz.ktcommon.http.response.MainListResponse
import com.wdz.ktcommon.response.LoginResponse
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.http.Field
import java.io.IOException
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.SocketException

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 15:12

 */
object NetRepository: BaseRepository() {
    /**
     * 首页相关
     * @param page Int
     * @return HttpResult<MainListResponse>
     */
    suspend fun getArticle(page: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.getArticle(page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    /**
     * 热搜词
     * @return
     */
    suspend fun getHotKey(): HttpResult<List<HotKeyResponse>> {
        return try {
            executeResponse(RetrofitManager.service.getHotKey())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }



    /**
     * 收藏相关
     * @param id Int
     * @return HttpResult<MainListResponse>
     */
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


    /**
     * 登录与注册
     */
    suspend fun login(username: String,password: String): HttpResult<LoginResponse> {
        return try {
            executeResponse(RetrofitManager.service.login(username,password))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    suspend fun register(username: String,password: String,repassword: String): HttpResult<ResponseBody> {
        return try {
            executeResponse(RetrofitManager.service.register(username,password,repassword))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    suspend fun logout(): HttpResult<Any> {
        return try {
            executeResponse(RetrofitManager.service.logout())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 搜索
     */
    suspend fun query(page: Int,k: String):HttpResult<CollectArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.query(page,k))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }





}