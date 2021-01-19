package com.wdz.main.main

import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeViewModel:BaseMvvmViewModel<HomeModel>(){

    var articleList:LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()

    override fun initModel() {
        model = HomeModel()
    }

    init {

    }

    fun getArticle(){
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(15)
            .setPrefetchDistance(3)
            .setInitialLoadSizeHint(15 * 3)
            .setMaxSize(65536 * 15)
            .build()

        articleList = LivePagedListBuilder<Int, MainArticle>(ArticleDataSourceFactory(), config).build()
    }
}