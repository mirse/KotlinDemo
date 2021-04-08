package com.wdz.module_article.system


import com.wdz.ktcommon.base.BaseModel

/**

 * @Author dezhi.wang

 * @Date 2021/3/8 14:46

 */
class SystemModel: BaseModel() {

    fun getSystem(onGetTreeListener: OnGetTreeListener){
        // TODO: 2021/4/2 内存泄漏
//        NetManager.getInstance().getTree(object: BaseObserver<List<TreeResponse>>(){
//            override fun onRequestSuccess(t: List<TreeResponse>?) {
//                if (t!=null){
//                    onGetTreeListener.onGetTreeSuccess(t)
//                }
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


    interface OnGetTreeListener{
        //fun onGetTreeSuccess(response: List<TreeResponse>)
    }
}