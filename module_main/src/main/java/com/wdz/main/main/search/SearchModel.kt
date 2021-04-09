package com.wdz.main.main.search

import android.content.Context
import androidx.lifecycle.LifecycleOwner





import com.wdz.ktcommon.base.BaseModel
import com.wdz.ktcommon.room.base.DatabaseOperationListener
import com.wdz.ktcommon.room.entity.History
import com.wdz.ktcommon.room.repository.HistoryRepository
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
//        NetManager.getInstance().getHotKey(object:BaseObserver<List<HotKeyResponse>>(){
//            override fun onRequestSuccess(t: List<HotKeyResponse>?) {
//                val mList = mutableListOf<String>()
//                if (t!=null){
//                    for (i in t.indices){
//                        val name = t.get(i).name
//                        mList.add(name)
//                    }
//                }
//
//                getHotKeyListener.getHotKeySuccess(mList)
//
//            }
//
//            override fun onRequestError(errorCode: Int, errorMsg: String?) {
//
//            }
//
//            override fun onRequestFailure(errorMsg: String?) {
//
//            }
//
//        })
    }

    /*
     * 获取搜索历史
     */
    fun getSearchHistory(databaseOperationListener: DatabaseOperationListener<History>){
        //HistoryRepository.getInstance(context)?.getAllHistory(databaseOperationListener)
    }

    /*
   * 保存搜索历史
   */
    fun saveSearchHistory(searchHistory: History,databaseOperationListener:DatabaseOperationListener<History>){
        HistoryRepository.getInstance(context)?.insertItems(databaseOperationListener,searchHistory)
    }

    interface GetHotKeyListener{
        fun getHotKeySuccess(response: List<String>?)
    }

}