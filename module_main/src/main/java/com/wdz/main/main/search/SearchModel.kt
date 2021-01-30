package com.wdz.main.main.search

import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class SearchModel: BaseModel() {
    private val TAG = this::class.simpleName


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

    interface GetHotKeyListener{
        fun getHotKeySuccess(response: List<String>?)
    }

}