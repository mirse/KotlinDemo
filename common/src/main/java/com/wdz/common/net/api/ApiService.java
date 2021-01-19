package com.wdz.common.net.api;

import com.wdz.common.net.base.BaseResponse;
import com.wdz.common.net.response.BannerResponse;
import com.wdz.common.net.response.CollectArticleResponse;
import com.wdz.common.net.response.CollectWebResponse;
import com.wdz.common.net.response.FriendResponse;
import com.wdz.common.net.response.HotKeyResponse;
import com.wdz.common.net.response.LoginResponse;
import com.wdz.common.net.response.MainListResponse;
import com.wdz.common.net.response.NaviResponse;
import com.wdz.common.net.response.ProjectInfoResponse;
import com.wdz.common.net.response.ProjectResponse;
import com.wdz.common.net.response.ShareArticleResponse;
import com.wdz.common.net.response.TopArticleResponse;
import com.wdz.common.net.response.TreeArticleResponse;
import com.wdz.common.net.response.TreeResponse;
import com.wdz.common.net.response.WxResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    //网络请求时长
    int HTTP_TIME = 0;
    String BasePath = "https://www.wanandroid.com";


    //1、首页相关
    /**
     * 首页文章列表
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Single<BaseResponse<MainListResponse>> getArticle(@Path("page") int page);

    /**
     * 首页banner
     * @return
     */
    @GET("banner/json")
    Single<BaseResponse<BannerResponse>> getBanner();

    /**
     * 常用网站
     * @return
     */
    @GET("friend/json")
    Single<BaseResponse<FriendResponse>> getFriend();

    /**
     * 热搜词
     * @return
     */
    @GET("hotkey/json")
    Single<BaseResponse<HotKeyResponse>> getHotKey();

    /**
     * 置顶文章
     * @return
     */
    @GET("article/top/json")
    Single<BaseResponse<TopArticleResponse>> getTopArticle();

    //2、体系
    /**
     * 体系数据
     * @return
     */
    @GET("tree/json")
    Single<BaseResponse<TreeResponse>> getTree();

    /**
     * 体系下的文章
     * @param page
     * @param cid
     * @return
     */
    @GET("article/list/{page}/json?cid={cid}")
    Single<BaseResponse<TreeArticleResponse>> getTreeArticle(@Path("page") int page, @Path("cid") int cid);

    /**
     * 按照作者昵称搜索文章
     * @param page
     * @param author
     * @return
     */
    @GET("article/list/{page}}/json?author={author}}")
    Single<BaseResponse<TreeArticleResponse>> getTreeArticleByAuthor(@Path("page") int page,@Path("author") String author);

    //3、导航数据
    /**
     * 导航数据
     * @return
     */
    @GET("navi/json")
    Single<BaseResponse<NaviResponse>> getNavi();

    //4、项目

    /**
     * 项目分类
     * @return
     */
    @GET("project/tree/json")
    Single<BaseResponse<ProjectResponse>> getProjectTree();

    /**
     * 项目列表数据
     * @return
     */
    @GET("project/list/{page}/json?cid={cid}}")
    Single<BaseResponse<ProjectInfoResponse>> getProjectInfo(@Path("page") int page, @Path("cid") int cid);

    //5、登录与注册
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Single<BaseResponse<LoginResponse>> login(@Field("username") String username,@Field("password") String password);


    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Single<BaseResponse<ResponseBody>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 退出
     */
    @GET("user/logout/json")
    Single<BaseResponse<BaseResponse.DataBean>> logout();

    //6、收藏

    /**
     * 收藏文章列表
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Single<BaseResponse<CollectArticleResponse>> getCollect(@Path("page") int page);

    /**
     * 收藏站内文章
     * @return
     */
    @POST("lg/collect/{id}}/json")
    Single<BaseResponse<BaseResponse.DataBean>> collectArticleIn(@Path("id") int id);

    /**
     * 收藏站外文章
     * @return
     */
    @POST("lg/collect/add/json")
    Single<BaseResponse<BaseResponse.DataBean>> collectArticleOut(@Path("title") String title,@Path("author") String author,@Path("link") String link);

    /**
     * 取消收藏 - 文章列表
     * @return
     */
    @POST("lg/uncollect_originId/{id}}/json")
    Single<BaseResponse<BaseResponse.DataBean>> uncollect(@Path("id") int id);

    /**
     * 取消收藏 - 我的收藏页面
     * @return
     */
    @POST("lg/uncollect/{id}}/json")
    Single<BaseResponse<BaseResponse.DataBean>> uncollect(@Path("id") int id,@Path("originId") int originId);

    /**
     * 收藏网站列表
     * @return
     */
    @GET("lg/collect/usertools/json")
    Single<BaseResponse<BaseResponse.DataBean>> collectWebList();

    /**
     * 收藏网址
     * @return
     */
    @POST("lg/collect/addtool/json")
    Single<BaseResponse<CollectWebResponse>> collectWeb(@Path("name") String name, @Path("link") String link);

    /**
     * 编辑收藏网址
     * @return
     */
    @POST("lg/collect/updatetool/json")
    Single<BaseResponse<CollectWebResponse>> updateWeb(@Path("id") int id,@Path("originId") int originId);

    /**
     * 删除收藏网址
     * @return
     */
    @POST("lg/collect/deletetool/json")
    Single<BaseResponse<BaseResponse.DataBean>> deleteWeb(@Path("id") int id);

    //7、搜索
    /**
     * 搜索
     * @return
     */
    @POST("article/query/{page}}/json")
    Single<BaseResponse<CollectArticleResponse>> query(@Path("page") int page,@Path("k") String k);

    //10、广场
    /**
     * 广场列表数据
     * @return
     */
    @GET("user_article/list/{page}}/json")
    Single<BaseResponse<CollectArticleResponse>> getUserArticle(@Path("page") int page);
    /**
     * 分享人对应列表数据
     * @return
     */
    @GET("user/{userId}}/share_articles/{page}}/json")
    Single<BaseResponse<ShareArticleResponse>> getShareArticle(@Path("userId") int userId, @Path("page") int page);
    /**
     * 自己的分享的文章列表
     * @return
     */
    @GET("user/lg/private_articles/{page}}/json")
    Single<BaseResponse<ShareArticleResponse>> getPrivateArticle(@Path("page") int page);
    /**
     * 删除自己分享的文章
     * @return
     */
    @POST("lg/user_article/delete/{id}}/json")
    Single<BaseResponse<BaseResponse.DataBean>> deleteShareArticle(@Path("id") int id);
    /**
     * 分享文章
     * @return
     */
    @POST("lg/user_article/add/json")
    Single<BaseResponse<BaseResponse.DataBean>> addUserArticle(@Path("title") String title,@Path("link") String link);

    //11、问答
    /**
     * 问答
     * @return
     */
    @GET("wenda/list/{pageId}}/json")
    Single<BaseResponse<CollectArticleResponse>> getAnswer(@Path("pageId") String pageId);

    //12、公众号tab
    /**
     * 获取公众号列表
     * @return
     */
    @GET("wxarticle/chapters/json")
    Single<BaseResponse<WxResponse>> getWxList();
    /**
     * 查看某个公众号历史数据
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Single<BaseResponse<CollectArticleResponse>> getWxArticle(@Path("id") int id,@Path("page") int page);
    /**
     * 在某个公众号中搜索历史文章
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json?k={k}")
    Single<BaseResponse<CollectArticleResponse>> searchWxArticle(@Path("id") int id,@Path("page") int page,@Path("k") String k);
}
