package com.wdz.main.main.search

import android.content.Context
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.base.room.DatabaseOperationListener
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.common.room.entity.History
import com.wdz.main.main.HomeModel
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory
import com.wdz.main.main.paging.SearchDataSourceFactory
import java.util.*

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchInfoViewModel:BaseMvvmViewModel<SearchInfoModel>(){
    var articleList: LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()

    override fun initModel(context: Context?) {
        model = SearchInfoModel()
    }


    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)//设置是否启用UI占用符
            .setPageSize(20)  //分页大小
            .setInitialLoadSizeHint(20)  //首次加载大小
            .setPrefetchDistance(10)  //预加载距离：还剩10个就要滑到底了，就进行预加载
            .build()
    }

    fun  getSearchList(searchName:String):LiveData<PagedList<MainArticle>>{
        val searchDataSourceFactory = SearchDataSourceFactory(searchName);
        searchDataSourceFactory.create()
        articleList = LivePagedListBuilder<Int, MainArticle>(searchDataSourceFactory, 20).build()
        return articleList
    }
}