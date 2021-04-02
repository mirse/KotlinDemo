package com.wdz.main.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Proxy

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeViewModel: BaseMvvmViewModel<HomeModel>(){
    private val TAG = this::class.simpleName
    var articleList: LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()
    var mainPageArticleList = MutableLiveData<MutableList<MainArticle>>()
    var otherPageArticleList = MutableLiveData<MutableList<MainArticle>>()


    public override fun initModel(context: Context?) {
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
        // TODO: 2021/3/31 retrofit版本需要2.6.0以上才能配合协程使用
        viewModelScope.launch(Dispatchers.Main) {
            val result = netRepository.getArticle(page)
            when(result){
                is HttpResult.Success ->{
                    val mList = mutableListOf<MainArticle>()
                    for (i in result.data.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = result.data.datas[i].link
                        mainArticle.chapterName = result.data.datas[i].chapterName
                        mainArticle.niceShareDate = result.data.datas[i].niceShareDate
                        mainArticle.title = result.data.datas[i].title
                        mainArticle.id = result.data.datas[i].id
                        mainArticle.collect = result.data.datas[i].collect
                        mList.add(mainArticle)
                    }
                    if (page == 0) mainPageArticleList.postValue(mList) else otherPageArticleList.postValue(mList)
                }
                is HttpResult.Error ->{

                }
            }



        }
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

    /**
     * 收藏文章
     * @param id Int
     */
    private fun collectArticle(id:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            when(netRepository.collectArticleIn(id))
            {
                is HttpResult.Success ->{
                    HttpRequestStatus.REQUEST_SUCCESS.setStatus("collect")
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
                }
                is HttpResult.Error ->{
                    val result = netRepository.collectArticleIn(id)
                    val errorMsg = (result as HttpResult.Error).exception.message
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
                }
            }
        }
//        model.collectArticle(id,object: HomeModel.OnCollectArticleListener{
//            override fun onCollectSuccess() {
//                HttpRequestStatus.REQUEST_SUCCESS.status = "collect"
//                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
//            }
//
//            override fun onCollectFail(msg: String?) {
//                httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(msg))
//            }
//
//        })
    }

    /**
     * 取消收藏文章
     * @param id Int
     */
    private fun unCollectArticle(id:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            when(netRepository.uncollect(id))
            {
                is HttpResult.Success ->{
                    HttpRequestStatus.REQUEST_SUCCESS.setStatus("unCollect")
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
                }
                is HttpResult.Error ->{
                    val result = netRepository.uncollect(id)
                    val errorMsg = (result as HttpResult.Error).exception.message
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
                }
            }
        }

//        model.unCollectArticle(id,object: HomeModel.OnUnCollectArticleListener{
//            override fun onUnCollectSuccess() {
//                HttpRequestStatus.REQUEST_SUCCESS.status = "unCollect"
//                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
//            }
//
//            override fun onUnCollectFail(msg: String?) {
//                httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(msg))
//            }
//
//        })
    }



}