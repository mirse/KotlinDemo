package com.wdz.common.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;

import androidx.databinding.BaseObservable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wdz.common.net.api.ApiService;
import com.wdz.common.net.base.BaseResponse;
import com.wdz.common.net.http.AddCookiesInterceptor;
import com.wdz.common.net.http.ReceivedCookiesInterceptor;
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

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;


import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

import static com.wdz.common.net.api.ApiService.BasePath;
import static com.wdz.common.net.api.ApiService.HTTP_TIME;

public class NetManager {
    private static final String TAG = "NetManager";
    private static NetManager mInstance;
    private String url = BasePath;
    private final ApiService apiService;

    public static NetManager getInstance() {
        if (mInstance == null) {
            synchronized (NetManager.class) {
                if (mInstance == null) {
                    mInstance = new NetManager();
                }
            }
        }
        return mInstance;
    }

    public NetManager() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                //设置连接超时时间
                .connectTimeout(HTTP_TIME, TimeUnit.SECONDS)
                //设置读取超时时间
                .readTimeout(HTTP_TIME, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

        apiService = retrofit.create(ApiService.class);
    }




    //1、首页相关
    /**
     * 首页文章列表
     * @param page
     * @param observer
     */
    public void getArticle(int page,BaseObserver<MainListResponse> observer){
        apiService.getArticle(page)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 首页banner
     * @param observer
     */
    public void getBanner(BaseObserver<BannerResponse> observer){
        apiService.getBanner()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 常用网站
     * @param observer
     */
    public void getFriend(BaseObserver<FriendResponse> observer){
        apiService.getFriend()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 热搜词
     * @param observer
     */
    public void getHotKey(BaseObserver<List<HotKeyResponse>> observer){
        apiService.getHotKey()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 置顶文章
     * @param observer
     */
    public void getTopArticle(BaseObserver<TopArticleResponse> observer){
        apiService.getTopArticle()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //2、体系
    /**
     * 体系数据
     * @param observer
     */
    public void getTree(BaseObserver<List<TreeResponse>> observer){
        apiService.getTree()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 体系下的文章
     * @param observer
     */
    public void getTreeArticle(int page,int cid,BaseObserver<TreeArticleResponse> observer){
        apiService.getTreeArticle(page,cid)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 按照作者昵称搜索文章
     * @param observer
     */
    public void getTreeArticleByAuthor(int page,String author,BaseObserver<TreeArticleResponse> observer){
        apiService.getTreeArticleByAuthor(page,author)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //3、导航数据
    /**
     * 导航数据
     * @param observer
     */
    public void getNavi(BaseObserver<NaviResponse> observer){
        apiService.getNavi()
                .compose(threadTransformer())
                .subscribe(observer);
    }
    //4、项目
    /**
     * 项目分类
     * @param observer
     */
    public void getProjectTree(BaseObserver<List<ProjectResponse>> observer){
        apiService.getProjectTree()
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 项目列表数据
     * @param observer
     */
    public void getProjectInfo(int page,int cid,BaseObserver<ProjectInfoResponse> observer){
        apiService.getProjectInfo(page,cid)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //5、登录与注册
    /**
     * 登录
     * @param observer
     */
    public void login(String username,String password,BaseObserver<LoginResponse> observer){
        apiService.login(username,password)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 注册
     * @param observer
     */
    public void register(String username,String password,String repassword,BaseObserver<BaseResponse.DataBean> observer){
        apiService.register(username,password,repassword)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    /**
     * 退出
     * @param observer
     */
    public void logout(BaseObserver<BaseResponse.DataBean> observer){
        apiService.logout()
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //6、收藏

    /**
     * 收藏文章列表
     * @param observer
     */
    public void getCollect(int page,BaseObserver<CollectArticleResponse> observer){
        apiService.getCollect(page)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 收藏站内文章
     * @param observer
     */
    public void collectArticleIn(int id,BaseObserver<BaseResponse.DataBean> observer){
        apiService.collectArticleIn(id)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 收藏站外文章
     * @param observer
     */
    public void collectArticleOut(String title,String author,String link,BaseObserver<BaseResponse.DataBean> observer){
        apiService.collectArticleOut(title,author,link)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 取消收藏 - 文章列表
     * @param observer
     */
    public void uncollect(int id,BaseObserver<BaseResponse.DataBean> observer){
        apiService.uncollect(id)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 取消收藏 - 我的收藏页面
     * @param observer
     */
    public void uncollect(int id,int originId,BaseObserver<BaseResponse.DataBean> observer){
        apiService.uncollect(id,originId)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 收藏网站列表
     * @param observer
     */
    public void collectWebList(BaseObserver<BaseResponse.DataBean> observer){
        apiService.collectWebList()
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 收藏网址
     * @param observer
     */
    public void collectWeb(String name,String link,BaseObserver<CollectWebResponse> observer){
        apiService.collectWeb(name,link)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 编辑收藏网址
     * @param observer
     */
    public void updateWeb(int id,int originId,BaseObserver<CollectWebResponse> observer){
        apiService.updateWeb(id,originId)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 删除收藏网址
     * @param observer
     */
    public void deleteWeb(int id,BaseObserver<BaseResponse.DataBean> observer){
        apiService.deleteWeb(id)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //7、搜索
    /**
     * 收藏网址
     * @param observer
     */
    public void query(int page,String k,BaseObserver<CollectArticleResponse> observer){
        apiService.query(page,k)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //10、广场
    /**
     * 广场列表数据
     * @param observer
     */
    public void getUserArticle(int page,BaseObserver<CollectWebResponse> observer){
        apiService.getUserArticle(page)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 分享人对应列表数据
     * @param observer
     */
    public void getShareArticle(int userId,int page, BaseObserver<ShareArticleResponse> observer){
        apiService.getShareArticle(userId, page)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 自己的分享的文章列表
     * @param observer
     */
    public void getPrivateArticle(int page,BaseObserver<ShareArticleResponse> observer){
        apiService.getPrivateArticle(page)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 删除自己分享的文章
     * @param observer
     */
    public void deleteShareArticle(int id,BaseObserver<BaseResponse.DataBean> observer){
        apiService.deleteShareArticle(id)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 分享文章
     * @param observer
     */
    public void addUserArticle(String title,String link,BaseObserver<BaseResponse.DataBean> observer){
        apiService.addUserArticle(title,link)
                .compose(threadTransformer())
                .subscribe(observer);
    }

    //11、问答
    /**
     * 问答
     * @param observer
     */
    public void getAnswer(String pageId,BaseObserver<CollectArticleResponse> observer){
        apiService.getAnswer(pageId)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    //12、公众号tab
    /**
     * 获取公众号列表
     * @param observer
     */
    public void getWxList(BaseObserver<List<WxResponse>> observer){
        apiService.getWxList()
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 查看某个公众号历史数据
     * @param observer
     */
    public void getWxArticle(int id,int page,BaseObserver<CollectArticleResponse> observer){
        apiService.getWxArticle(id,page)
                .compose(threadTransformer())
                .subscribe(observer);
    }
    /**
     * 在某个公众号中搜索历史文章
     * @param observer
     */
    public void searchWxArticle(int id,int page,String k,BaseObserver<CollectWebResponse> observer){
        apiService.searchWxArticle(id,page,k)
                .compose(threadTransformer())
                .subscribe(observer);
    }












    public SingleTransformer threadTransformer(){
        return new SingleTransformer() {
            @NonNull
            @Override
            public SingleSource apply(@NonNull Single upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public FlowableTransformer flowableTransformer(){
        return new FlowableTransformer() {
            @NonNull
            @Override
            public Publisher apply(@NonNull Flowable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }


        };
    }
}
