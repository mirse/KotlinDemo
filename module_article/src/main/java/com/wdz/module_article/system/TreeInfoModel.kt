package com.wdz.module_article.system


import com.wdz.ktcommon.base.BaseModel

/**

 * @Author dezhi.wang

 * @Date 2021/3/9 9:08

 */
class TreeInfoModel: BaseModel() {
    fun getArticle(onGetWxListener:OnGetWxListener){
//        NetManager.getInstance().getWxList(object : BaseObserver<List<WxResponse>>(){
//            override fun onRequestSuccess(t: List<WxResponse>?) {
//                if (t!=null){
//                    onGetWxListener.onGetWxSuccess(t)
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


    fun getCategory(onCategoryListener:OnCategoryListener){
//        NetManager.getInstance().getProjectTree(object :BaseObserver<List<ProjectResponse>>(){
//            override fun onRequestSuccess(t: List<ProjectResponse>?) {
//                if (t!=null){
//                    onCategoryListener.onCategorySuccess(t)
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
//
//        })
    }

    interface OnGetWxListener{
        //fun onGetWxSuccess(response: List<WxResponse>)
    }

    interface OnCategoryListener{
        //fun onCategorySuccess(response: List<ProjectResponse>)
    }
}