package com.wdz.main.main

import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeModel: BaseModel() {
    private val TAG = this::class.simpleName


    fun getMyArticle(page:Int,onGetArticleListener: OnGetArticleListener){
        NetManager.getInstance().getArticle(page,object: BaseObserver<MainListResponse>(){
            override fun onRequestSuccess(t: MainListResponse?) {
                if (t!=null){
                    val mList = mutableListOf<MainArticle>()
                    for (i in t.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = t.datas[i].link
                        mainArticle.chapterName = t.datas[i].chapterName
                        mainArticle.niceShareDate = t.datas[i].niceShareDate
                        mainArticle.title = t.datas[i].title
                        mainArticle.id = t.datas[i].id
                        mainArticle.collect = t.datas[i].collect
                        mList.add(mainArticle)
                    }
                    onGetArticleListener.onGetSuccess(mList)

                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    fun collectArticle(id:Int,onCollectArticleListener: OnCollectArticleListener) {
        NetManager.getInstance().collectArticleIn(id,object : BaseObserver<BaseResponse.DataBean>(){
            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
                onCollectArticleListener.onCollectSuccess()
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }
    fun unCollectArticle(id:Int,onUnCollectSuccess: OnUnCollectArticleListener,) {
        NetManager.getInstance().uncollect(id,object : BaseObserver<BaseResponse.DataBean>(){
            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
                onUnCollectSuccess.onUnCollectSuccess()
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    interface OnGetArticleListener{
        fun onGetSuccess(list:MutableList<MainArticle>);
    }

    interface OnCollectArticleListener{
        fun onCollectSuccess();
    }

    interface OnUnCollectArticleListener{
        fun onUnCollectSuccess();
    }

}