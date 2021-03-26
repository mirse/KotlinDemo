package com.wdz.main.main

import android.content.Context
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory
import java.lang.reflect.Proxy

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeViewModel:BaseMvvmViewModel<HomeModel>(){

    var articleList: LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()
    var mainPageArticleList = MutableLiveData<MutableList<MainArticle>>()
    var otherPageArticleList = MutableLiveData<MutableList<MainArticle>>()
    var collectStatus = MutableLiveData<Int>()
    var unCollectStatus = MutableLiveData<Int>()


    override fun initModel(context: Context) {
        model = HomeModel()
    }

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)//设置是否启用UI占用符
            .setPageSize(20)  //分页大小
            .setInitialLoadSizeHint(20)  //首次加载大小
            .setPrefetchDistance(10)  //预加载距离：还剩10个就要滑到底了，就进行预加载
            .build()
        val articleDataSourceFactory = ArticleDataSourceFactory()
        articleDataSourceFactory.create()
        // TODO: 2021/3/23 加载数目少于当页显示的数目时会怎么样？
        articleList = LivePagedListBuilder<Int, MainArticle>(articleDataSourceFactory, config).build()
    }

    fun  getArticle():LiveData<PagedList<MainArticle>>{
        return articleList
    }

    fun getMyArticle(page:Int){
        model.getMyArticle(page,object : HomeModel.OnGetArticleListener{
            override fun onGetSuccess(list: MutableList<MainArticle>) {
                if (page == 0) mainPageArticleList.postValue(list) else otherPageArticleList.postValue(list)

            }

        })
    }

    fun onClickCollect(data: MainArticle){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        if (data.collect){
            unCollectArticle(data.id)
        }
        else{
            collectArticle(data.id)
        }
    }

    fun collectArticle(id:Int) {
        model.collectArticle(id,object: HomeModel.OnCollectArticleListener{
            override fun onCollectSuccess() {
                collectStatus.postValue(id)
                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS)
            }

        })
    }
    fun unCollectArticle(id:Int) {
        model.unCollectArticle(id,object: HomeModel.OnUnCollectArticleListener{
            override fun onUnCollectSuccess() {
                unCollectStatus.postValue(id)
                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS)
            }

        })
    }

}