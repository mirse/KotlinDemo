package com.wdz.main.main.search

import android.content.Context
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.room.base.DatabaseOperationListener
import com.wdz.ktcommon.room.entity.History
import com.wdz.main.main.bean.MainArticle
import kotlinx.coroutines.launch
import java.util.*

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchViewModel: BaseMvvmViewModel<SearchModel>(){

    val hotKeyList = MutableLiveData<List<String>>()
    val searchHistoryList = MutableLiveData<MutableList<History>>()
    public override fun initModel(context:Context) {
        model = SearchModel(context)

    }

    fun getHotKey(){
        viewModelScope.launch {
            val result = NetRepository.getHotKey()
            when(result){
                is HttpResult.Success ->{
                    val mList = mutableListOf<String>()
                    result.data?.run {
                        for (i in indices){
                            val name = get(i).name
                            mList.add(name)
                        }
                    }

                    hotKeyList.postValue(mList)
                }
                is HttpResult.Error ->{

                }
            }

        }

    }



    fun getSearchHistory(){
        model?.getSearchHistory(object: DatabaseOperationListener<History> {
            override fun onFailure() {

            }
            override fun onSuccess(items: List<History>?) {
                val list = items?.toMutableList()
                searchHistoryList.postValue(list)
            }
        })

    }

    fun saveSearchHistory(searchHistory: History){
        model?.saveSearchHistory(searchHistory,object:DatabaseOperationListener<History>{

            override fun onFailure() {

            }

            override fun onSuccess(items: List<History>?) {

            }

        })

    }
}