package com.wdz.main.main

import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.MainListResponse

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeViewModel:BaseMvvmViewModel<HomeModel>(){
    override fun initModel() {
        model = HomeModel()
    }
    fun getArticle(page:Int){
        model.getArticle(page,object:HomeModel.GetArticleListener{
            override fun getArticleSuccess(response: MainListResponse?) {

            }

            override fun getArticleFail(errorMsg: String) {

            }

        })
    }
}