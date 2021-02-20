package com.wdz.main.main.search

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.wdz.common.base.room.DatabaseOperationListener
import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.CollectArticleResponse
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.common.room.entity.History
import com.wdz.common.room.repository.HistoryRepository
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchModel(context: Context): BaseModel() {
    private val TAG = this::class.simpleName

    private val context:Context = context


    /*
    * 获取热搜词
    */
    fun getHotKey(getHotKeyListener:GetHotKeyListener){
        NetManager.getInstance().getHotKey(object:BaseObserver<List<HotKeyResponse>>(){
            override fun onRequestSuccess(t: List<HotKeyResponse>?) {
                val mList = mutableListOf<String>()
                if (t!=null){
                    for (i in t.indices){
                        val name = t.get(i).name
                        mList.add(name)
                    }
                }

                getHotKeyListener.getHotKeySuccess(mList)

            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    /*
     * 获取搜索历史
     */
    fun getSearchHistory(lifecycleOwner:LifecycleOwner,databaseOperationListener:DatabaseOperationListener<History>){
        HistoryRepository.getInstance(context,lifecycleOwner).getAllHistory(databaseOperationListener)
    }

    /*
   * 保存搜索历史
   */
    fun saveSearchHistory(searchHistory: History,lifecycleOwner:LifecycleOwner,databaseOperationListener:DatabaseOperationListener<History>){
        HistoryRepository.getInstance(context,lifecycleOwner).insertItems(databaseOperationListener,searchHistory)
    }

    interface GetHotKeyListener{
        fun getHotKeySuccess(response: List<String>?)
    }

}