package com.wdz.main.main.search

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.room.base.DatabaseOperationListener
import com.wdz.ktcommon.room.entity.History
import com.wdz.ktcommon.room.repository.HistoryRepository
import com.wdz.main.main.bean.MainArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchViewModel(application: Application) : BaseMvvmViewModel<SearchModel>(application) {
    private val TAG = this::class.simpleName
    val hotKeyList = MutableLiveData<List<String>>()
    var searchHistoryList = MutableLiveData<MutableList<History>>()
    public override fun initModel(context: Context) {
        model = SearchModel(context)

    }

    fun getHotKey() {
        viewModelScope.launch {
            val result = NetRepository.getHotKey()
            when (result) {
                is HttpResult.Success -> {
                    val mList = mutableListOf<String>()
                    result.data?.run {
                        for (i in indices) {
                            val name = get(i).name
                            mList.add(name)
                        }
                    }

                    hotKeyList.postValue(mList)
                }
                is HttpResult.Error -> {

                }
            }

        }

    }


    fun getSearchHistory() {
//        searchHistoryList = HistoryRepository.getInstance(getApplication())?.getAllHistory()
//            ?.distinctUntilChanged()
//            ?.catch {
//                ex ->
//                ex.printStackTrace()
//            }.asLiveData()

        viewModelScope.launch(Dispatchers.Main){//消费线程在我们启动协程指定调度器的时候就确认
            HistoryRepository.getInstance(getApplication())?.run {
                getAllHistory()?.run {
                    flowOn(Dispatchers.IO)//改变数据发射的线程
                    .onStart {
                         //在调用flow数据之前，做准备工作
                     }
                    .catch {
                        //捕获上游出现的异常
                        e -> e.printStackTrace()
                    }
                    .onCompletion {
                        //对应rxjava->onComplete
                    }
                    .collectLatest {
                        //末端操作符，只处理最新的数据
                        //类似订阅
                        searchHistoryList.postValue(it.toMutableList() )
                    }
                }
            }
        }

    }

    fun saveSearchHistory(searchHistory: History) {
        model?.saveSearchHistory(searchHistory, object : DatabaseOperationListener<History> {

            override fun onFailure() {

            }

            override fun onSuccess(items: List<History>?) {

            }

        })

    }
}