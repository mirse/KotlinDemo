package com.wdz.ktcommon.http.coroutines



import com.wdz.ktcommon.base.BaseResp
import com.wdz.ktcommon.http.response.*
import com.wdz.ktcommon.response.LoginResponse
import com.wdz.ktcommon.response.TreeResponse
import com.wdz.ktcommon.response.WxResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

/**

 * @Author dezhi.wang

 * @Date 2021/3/30 10:47

 */
interface NetService {

    //1、首页相关
    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): BaseResp<MainListResponse>

    /**
     * 首页banner
     * @return
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResp<BannerResponse>

    /**
     * 常用网站
     * @return
     */
    @GET("friend/json")
    suspend fun getFriend(): BaseResp<FriendResponse>

    /**
     * 热搜词
     * @return
     */
    @GET("hotkey/json")
    suspend fun getHotKey(): BaseResp<List<HotKeyResponse>>

    /**
     * 置顶文章
     * @return
     */
    @GET("article/top/json")
    suspend fun getTopArticle(): BaseResp<TopArticleResponse>

    //2、体系
    //2、体系
    /**
     * 体系数据
     * @return
     */
    @GET("tree/json")
    suspend fun getTree(): BaseResp<List<TreeResponse>>

    /**
     * 体系下的文章
     * @param page
     * @param cid
     * @return
     */
    @GET("article/list/{page}/json")
    suspend fun getTreeArticle(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResp<TreeArticleResponse>

    /**
     * 按照作者昵称搜索文章
     * @param page
     * @param author
     * @return
     */
    @GET("article/list/{page}}/json?author={author}}")
    suspend fun getTreeArticleByAuthor(
        @Path("page") page: Int,
        @Path("author") author: String?
    ): BaseResp<TreeArticleResponse>

    //3、导航数据
    //3、导航数据
    /**
     * 导航数据
     * @return
     */
    @GET("navi/json")
    suspend fun getNavi(): BaseResp<NaviResponse>

    //4、项目

    //4、项目
    /**
     * 项目分类
     * @return
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): BaseResp<List<ProjectResponse>>

    /**
     * 项目列表数据
     * @return
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectInfo(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResp<ProjectInfoResponse>

    //5、登录与注册
    //5、登录与注册
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResp<LoginResponse>


    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseResp<ResponseBody>

    /**
     * 退出
     */
    @GET("user/logout/json")
    suspend fun logout(): BaseResp<ResponseBody>

    //6、收藏

    //6、收藏
    /**
     * 收藏文章列表
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollect(@Path("page") page: Int): BaseResp<CollectArticleResponse>

    /**
     * 收藏站内文章
     * @return
     */
    @POST("lg/collect/{id}/json")
    suspend fun collectArticleIn(@Path("id") id: Int): BaseResp<Nothing>

    /**
     * 收藏站外文章
     * @return
     */
    @POST("lg/collect/add/json")
    suspend fun collectArticleOut(
        @Path("title") title: String?,
        @Path("author") author: String?,
        @Path("link") link: String?
    ): BaseResp<Nothing>

    /**
     * 取消收藏 - 文章列表
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun uncollect(@Path("id") id: Int): BaseResp<Nothing>

    /**
     * 取消收藏 - 我的收藏页面
     * @return
     */
    @POST("lg/uncollect/{id}}/json")
    suspend fun uncollect(
        @Path("id") id: Int,
        @Path("originId") originId: Int
    ): BaseResp<Nothing>

    /**
     * 收藏网站列表
     * @return
     */
    @GET("lg/collect/usertools/json")
    suspend fun collectWebList(): BaseResp<Nothing>

    /**
     * 收藏网址
     * @return
     */
    @POST("lg/collect/addtool/json")
    suspend fun collectWeb(
        @Path("name") name: String?,
        @Path("link") link: String?
    ): BaseResp<CollectWebResponse>

    /**
     * 编辑收藏网址
     * @return
     */
    @POST("lg/collect/updatetool/json")
    suspend fun updateWeb(
        @Path("id") id: Int,
        @Path("originId") originId: Int
    ): BaseResp<CollectWebResponse>

    /**
     * 删除收藏网址
     * @return
     */
    @POST("lg/collect/deletetool/json")
    suspend fun deleteWeb(@Path("id") id: Int): BaseResp<Nothing>

    //7、搜索
    /**
     * todo
     * GET:
     * @path 把传递的参数直接拼在url后面，配合{}使用
     * @Query 把key-value拼接在url后面，不使用{}
     * @QueryMap 与 @Query差不多，参数多的时候使用
     *
     * POST:
     * 标记类：
     * @Field/@FieldMap一般配合@FormUrlEncoded使用
     * @Multipart与@Post使用，做文件的上传
     * @Streaming 大文件的下载
     *
     * @Body适用于post请求，将请求参数放到请求体中
     * 搜索
     * @param page
     * @param k
     * @return
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    suspend fun query(
        @Path(value = "page") page: Int,
        @Field("k") k: String
    ): BaseResp<CollectArticleResponse>

    //10、广场
    //10、广场
    /**
     * 广场列表数据
     * @return
     */
    @GET("user_article/list/{page}}/json")
    suspend fun getUserArticle(@Path("page") page: Int): BaseResp<CollectArticleResponse>

    /**
     * 分享人对应列表数据
     * @return
     */
    @GET("user/{userId}}/share_articles/{page}}/json")
    suspend fun getShareArticle(
        @Path("userId") userId: Int,
        @Path("page") page: Int
    ): BaseResp<ShareArticleResponse>

    /**
     * 自己的分享的文章列表
     * @return
     */
    @GET("user/lg/private_articles/{page}}/json")
    suspend fun getPrivateArticle(@Path("page") page: Int): BaseResp<ShareArticleResponse>

    /**
     * 删除自己分享的文章
     * @return
     */
    @POST("lg/user_article/delete/{id}}/json")
    suspend fun deleteShareArticle(@Path("id") id: Int): BaseResp<Nothing>

    /**
     * 分享文章
     * @return
     */
    @POST("lg/user_article/add/json")
    suspend fun addUserArticle(
        @Path("title") title: String?,
        @Path("link") link: String?
    ): BaseResp<Nothing>

    //11、问答
    //11、问答
    /**
     * 问答
     * @return
     */
    @GET("wenda/list/{pageId}}/json")
    suspend fun getAnswer(@Path("pageId") pageId: String?): BaseResp<CollectArticleResponse>

    //12、公众号tab
    //12、公众号tab
    /**
     * 获取公众号列表
     * @return
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWxList(): BaseResp<List<WxResponse>>

    /**
     * 查看某个公众号历史数据
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getWxArticle(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): BaseResp<CollectArticleResponse>

    /**
     * 在某个公众号中搜索历史文章
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json?k={k}")
    suspend fun searchWxArticle(
        @Path("id") id: Int,
        @Path("page") page: Int,
        @Path("k") k: String?
    ): BaseResp<CollectArticleResponse>
}