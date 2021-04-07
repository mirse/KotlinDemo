package com.wdz.main.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.main.main.bean.MainArticle

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Proxy

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 11:53

 */
class HomeViewModel: BaseMvvmViewModel<HomeModel>(){
    private val TAG = this::class.simpleName
    var mainPageArticleList = MutableLiveData<MutableList<MainArticle>>()
    var otherPageArticleList = MutableLiveData<MutableList<MainArticle>>()


    public override fun initModel(context: Context) {
        model = HomeModel()
    }





    fun getMyArticle(page:Int){
        // TODO: 2021/3/31 retrofit版本需要2.6.0以上才能配合协程使用
        viewModelScope.launch(Dispatchers.Main) {
            val result = NetRepository.getArticle(page)
            when(result){
                is HttpResult.Success ->{
                    val mList = mutableListOf<MainArticle>()
                    result.data?.run {
                        for (i in datas.indices){
                            val mainArticle = MainArticle()
                            mainArticle.link = datas[i].link
                            mainArticle.chapterName = datas[i].chapterName
                            mainArticle.niceShareDate = datas[i].niceShareDate
                            mainArticle.title = datas[i].title
                            mainArticle.id = datas[i].id
                            mainArticle.collect = datas[i].collect
                            mList.add(mainArticle)
                        }
                    }

                    if (page == 0) mainPageArticleList.postValue(mList) else otherPageArticleList.postValue(mList)
                }
                is HttpResult.Error ->{

                }
            }



        }
    }

    fun onClickCollect(data: MainArticle){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        if (data.collect){
            unCollectArticle(data.id)
        }
        else{
            collectArticle(data.id)
        }
    }

    /**
     * 收藏文章
     * @param id Int
     */
    private fun collectArticle(id:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            when(NetRepository.collectArticleIn(id))
            {
                is HttpResult.Success ->{
                    HttpRequestStatus.REQUEST_SUCCESS.setStatus("collect")
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
                }
                is HttpResult.Error ->{
                    val result = NetRepository.collectArticleIn(id)
                    val errorMsg = (result as HttpResult.Error).exception.message
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
                }
            }
        }

    }

    /**
     * 取消收藏文章
     * @param id Int
     */
    private fun unCollectArticle(id:Int) {
        viewModelScope.launch(Dispatchers.Main) {
            when(NetRepository.uncollect(id))
            {
                is HttpResult.Success ->{
                    HttpRequestStatus.REQUEST_SUCCESS.setStatus("unCollect")
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(id))
                }
                is HttpResult.Error ->{
                    val result = NetRepository.uncollect(id)
                    val errorMsg = (result as HttpResult.Error).exception.message
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
                }
            }
        }

    }



}