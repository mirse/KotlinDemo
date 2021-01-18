package com.wdz.main.main

import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.common.util.Log

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeModel: BaseModel() {
    private val TAG = this::class.simpleName
    fun getArticle(page:Int,getArticleListener:GetArticleListener){
        NetManager.getInstance().getArticle(page,object: BaseObserver<MainListResponse>(){
            override fun onRequestSuccess(t: MainListResponse?) {
                getArticleListener.getArticleSuccess(t)
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {
                if (errorMsg != null) {
                    getArticleListener.getArticleFail(errorMsg)
                }
                else{
                    getArticleListener.getArticleFail("获取文章失败")
                }
            }

            override fun onRequestFailure(errorMsg: String?) {
                if (errorMsg != null) {
                    getArticleListener.getArticleFail(errorMsg)
                }
                else{
                    getArticleListener.getArticleFail("获取文章失败")
                }
            }

        })
    }

    interface GetArticleListener{
        fun getArticleSuccess(response: MainListResponse?)
        fun getArticleFail(errorMsg:String)
    }
}