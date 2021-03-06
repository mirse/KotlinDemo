package com.wdz.main.main


import com.wdz.ktcommon.base.BaseModel
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeModel: BaseModel() {
    private val TAG = this::class.simpleName


    fun getMyArticle(page:Int,onGetArticleListener: OnGetArticleListener){
//        NetManager.getInstance().getArticle(page,object: BaseObserver<MainListResponse>(){
//            override fun onRequestSuccess(t: MainListResponse?) {
//                if (t!=null){
//                    val mList = mutableListOf<MainArticle>()
//                    for (i in t.datas.indices){
//                        val mainArticle = MainArticle()
//                        mainArticle.link = t.datas[i].link
//                        mainArticle.chapterName = t.datas[i].chapterName
//                        mainArticle.niceShareDate = t.datas[i].niceShareDate
//                        mainArticle.title = t.datas[i].title
//                        mainArticle.id = t.datas[i].id
//                        mainArticle.collect = t.datas[i].collect
//                        mList.add(mainArticle)
//                    }
//                    onGetArticleListener.onGetSuccess(mList)
//
//                }
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

    fun collectArticle(id:Int,onCollectArticleListener: OnCollectArticleListener) {
//        NetManager.getInstance().collectArticleIn(id,object : BaseObserver<BaseResponse.DataBean>(){
//            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
//                onCollectArticleListener.onCollectSuccess()
//            }
//
//            override fun onRequestError(errorCode: Int, errorMsg: String?) {
//                onCollectArticleListener.onCollectFail(errorMsg)
//            }
//
//            override fun onRequestFailure(errorMsg: String?) {
//                onCollectArticleListener.onCollectFail(errorMsg)
//            }
//
//        })
    }
    fun unCollectArticle(id:Int,onUnCollectSuccess: OnUnCollectArticleListener,) {
//        NetManager.getInstance().uncollect(id,object : BaseObserver<BaseResponse.DataBean>(){
//            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
//                onUnCollectSuccess.onUnCollectSuccess()
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

    interface OnGetArticleListener{
        fun onGetSuccess(list:MutableList<MainArticle>);
    }

    /**
     * 收藏文章监听
     */
    interface OnCollectArticleListener{
        fun onCollectSuccess();
        fun onCollectFail(msg:String?)
    }

    interface OnUnCollectArticleListener{
        fun onUnCollectSuccess()
        fun onUnCollectFail(msg:String?)
    }

}