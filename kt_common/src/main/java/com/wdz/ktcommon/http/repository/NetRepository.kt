package com.wdz.ktcommon.http.repository



import android.net.ParseException
import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.wdz.ktcommon.base.BaseRepository
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.RetrofitManager
import com.wdz.ktcommon.http.response.*
import com.wdz.ktcommon.response.LoginResponse
import com.wdz.ktcommon.response.TreeResponse
import com.wdz.ktcommon.response.WxResponse
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.http.Field
import retrofit2.http.Path
import java.io.IOException
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.SocketException

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 15:12

 */
object NetRepository: BaseRepository() {
    //1、首页相关
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
     * 首页banner
     */
    suspend fun getBanner(): HttpResult<BannerResponse> {
        return try {
            executeResponse(RetrofitManager.service.getBanner())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 常用网站
     */
    suspend fun getFriend(): HttpResult<FriendResponse> {
        return try {
            executeResponse(RetrofitManager.service.getFriend())
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
     * 置顶文章
     * @return
     */
    suspend fun getTopArticle(): HttpResult<TopArticleResponse> {
        return try {
            executeResponse(RetrofitManager.service.getTopArticle())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    //2、体系
    //2、体系
    /**
     * 体系数据
     * @return
     */
    suspend fun getTree(): HttpResult<List<TreeResponse>> {
        return try {
            executeResponse(RetrofitManager.service.getTree())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 体系下的文章
     * @return
     */
    suspend fun getTreeArticle(page: Int,cid: Int): HttpResult<TreeArticleResponse> {
        return try {
            executeResponse(RetrofitManager.service.getTreeArticle(page,cid))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 体系下的文章
     * @return
     */
    suspend fun getTreeArticleByAuthor(page: Int,author: String): HttpResult<TreeArticleResponse> {
        return try {
            executeResponse(RetrofitManager.service.getTreeArticleByAuthor(page,author))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    //3、导航数据
    //3、导航数据

    /**
     * 导航数据
     * @return
     */
    suspend fun getNavi(): HttpResult<NaviResponse> {
        return try {
            executeResponse(RetrofitManager.service.getNavi())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    //4、项目

    //4、项目
    /**
     * 项目分类
     * @return
     */
    suspend fun getProjectTree(): HttpResult<List<ProjectResponse>> {
        return try {
            executeResponse(RetrofitManager.service.getProjectTree())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 项目列表数据
     * @return
     */
    suspend fun getProjectInfo(page: Int,cid: Int): HttpResult<ProjectInfoResponse> {
        return try {
            executeResponse(RetrofitManager.service.getProjectInfo(page, cid))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    //5、登录与注册
    //5、登录与注册
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

    //6、收藏

    //6、收藏
    /**
     * 收藏文章列表
     */
    suspend fun getCollect(page: Int): HttpResult<CollectArticleResponse> {
        return try {
            executeResponse(RetrofitManager.service.getCollect(page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 收藏站内文章
     */
    suspend fun collectArticleIn(id: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.collectArticleIn(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    /**
     * 收藏站外文章
     */
    suspend fun collectArticleOut(title: String, author: String, link: String): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.collectArticleOut(title,author,link))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 取消收藏 - 文章列表
     * @param id Int
     * @return HttpResult<MainListResponse>
     */
    suspend fun uncollect(id: Int): HttpResult<MainListResponse> {
        return try {
            executeResponse(RetrofitManager.service.uncollect(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 收藏网址
     * @param id Int
     * @return HttpResult<MainListResponse>
     */
    suspend fun collectWeb(name:String,link: String): HttpResult<CollectWebResponse> {
        return try {
            executeResponse(RetrofitManager.service.collectWeb(name,link))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    /**
     * 编辑收藏网址
     * @param id Int
     * @return HttpResult<MainListResponse>
     */
    suspend fun updateWeb(id:Int,originId: Int): HttpResult<CollectWebResponse> {
        return try {
            executeResponse(RetrofitManager.service.updateWeb(id,originId))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 删除收藏网址
     * @param id Int
     * @return HttpResult<MainListResponse>
     */
    suspend fun deleteWeb(id:Int): HttpResult<Nothing> {
        return try {
            executeResponse(RetrofitManager.service.deleteWeb(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }


    //7、搜索
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
    //10、广场
    //10、广场
    /**
     * 广场列表数据
     * @return
     */
    suspend fun getUserArticle(page: Int):HttpResult<CollectArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.getUserArticle(page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 分享人对应列表数据
     * @return
     */
    suspend fun getShareArticle(userId: Int,page: Int):HttpResult<ShareArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.getShareArticle(userId,page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    /**
     * 自己的分享的文章列表
     * @return
     */
    suspend fun getPrivateArticle(page: Int):HttpResult<ShareArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.getPrivateArticle(page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 删除自己分享的文章
     * @return
     */
    suspend fun deleteShareArticle(id: Int):HttpResult<Nothing>{
        return try {
            executeResponse(RetrofitManager.service.deleteShareArticle(id))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 分享文章
     * @return
     */
    suspend fun addUserArticle(title: String,link: String):HttpResult<Nothing>{
        return try {
            executeResponse(RetrofitManager.service.addUserArticle(title,link))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    //11、问答
    //11、问答
    /**
     * 问答
     * @return
     */
    suspend fun getAnswer(pageId: String):HttpResult<CollectArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.getAnswer(pageId))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }
    //12、公众号tab
    //12、公众号tab
    /**
     * 获取公众号列表
     * @return
     */
    suspend fun getWxList():HttpResult<List<WxResponse>>{
        return try {
            executeResponse(RetrofitManager.service.getWxList())
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 查看某个公众号历史数据
     * @return
     */
    suspend fun getWxArticle(id: Int,page: Int):HttpResult<CollectArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.getWxArticle(id,page))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }

    /**
     * 在某个公众号中搜索历史文章
     * @return
     */
    suspend fun searchWxArticle(id: Int,page: Int,k: String):HttpResult<CollectArticleResponse>{
        return try {
            executeResponse(RetrofitManager.service.searchWxArticle(id,page,k))
        } catch (e:Exception){
            HttpResult.Error(IOException(getErrorType(e)))
        }
    }


}