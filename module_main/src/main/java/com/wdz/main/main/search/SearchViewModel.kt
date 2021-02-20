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
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory
import java.util.*

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchViewModel:BaseMvvmViewModel<SearchModel>(){

    val hotKeyList = MutableLiveData<List<String>>()
    val searchHistoryList = MutableLiveData<MutableList<History>>()
    override fun initModel(context:Context) {
        model = SearchModel(context)

    }

    fun getHotKey(){
        model.getHotKey(object : SearchModel.GetHotKeyListener{
            override fun getHotKeySuccess(response: List<String>?) {
                hotKeyList.postValue(response)
            }
        })
    }



    fun getSearchHistory(){
        model.getSearchHistory(getLifecycleOwner(),object: DatabaseOperationListener<History>{
            override fun onSuccess(items: MutableList<History>?) {
                val list = items?.toMutableList()
                searchHistoryList.postValue(list)
            }

            override fun onFailure() {

            }
        })
    }

    fun saveSearchHistory(searchHistory: History){
        model.saveSearchHistory(searchHistory,getLifecycleOwner(),object:DatabaseOperationListener<History>{
            override fun onSuccess(items: MutableList<History>?) {

            }

            override fun onFailure() {

            }

        })
    }
}