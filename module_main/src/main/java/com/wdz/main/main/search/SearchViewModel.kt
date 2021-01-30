package com.wdz.main.main.search

import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle
import com.wdz.main.main.paging.ArticleDataSourceFactory

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchViewModel:BaseMvvmViewModel<SearchModel>(){

    val hotKeyList = MutableLiveData<List<String>>()
    override fun initModel() {
        model = SearchModel()
    }

    fun getHotKey(){
        model.getHotKey(object : SearchModel.GetHotKeyListener{
            override fun getHotKeySuccess(response: List<String>?) {
                hotKeyList.postValue(response)
            }
        })
    }
}