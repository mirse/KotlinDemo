package com.wdz.main.main.paging

import androidx.paging.PageKeyedDataSource

import com.wdz.ktcommon.base.HttpResult

import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.main.main.bean.MainArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* PositionalDataSource-> 适用于目标数据总数固定，通过特定的位置加载
* ItemKeyedDataSource -> 适用于目标数据的加载依赖特定条目，比如根据第N条的信息加载第N+1条的数据
* PageKeyedDataSource -> 适用于目标数据根据页信息请求数据的场景，即参数中包含next/previous等类似页数信息
*
*/
public class PositionSearchDataSource(searchName:String): PageKeyedDataSource<String,MainArticle>(){
    val mSearchName = searchName;
    private var mPage = 0
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, MainArticle>
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = NetRepository.query(mPage, mSearchName)
            when(result){
                is HttpResult.Success -> {

                    val mList = mutableListOf<MainArticle>()
                    result.data?.run {
                        for (i in datas.indices){
                            val mainArticle = MainArticle()
                            mainArticle.link = datas[i].link
                            mainArticle.chapterName = datas[i].chapterName
                            mainArticle.niceShareDate = datas[i].niceShareDate
                            mainArticle.title = datas[i].title
                            mList.add(mainArticle)
                        }
                    }

                    withContext(Dispatchers.Main){
                        callback.onResult(mList,"before","after")
                    }

                }

                else -> {

                }
            }
        }
//        NetManager.getInstance().query(mPage,mSearchName,object: BaseObserver<CollectArticleResponse>(){
//            override fun onRequestSuccess(t: CollectArticleResponse?) {
//                if (t!=null){
//                    val mList = mutableListOf<MainArticle>()
//                    for (i in t.datas.indices){
//                        t.datas[i].apkLink
//                        val mainArticle = MainArticle()
//                        mainArticle.link = t.datas[i].link
//                        mainArticle.chapterName = t.datas[i].chapterName
//                        mainArticle.niceShareDate = t.datas[i].niceShareDate
//                        mainArticle.title = t.datas[i].title
//                        mList.add(mainArticle)
//                    }
//                    callback.onResult(mList,"before","after")
//
//
//                }
//            }
//            override fun onRequestError(errorCode: Int, errorMsg: String?) {
//
//            }
//
//            override fun onRequestFailure(errorMsg: String?) {
//
//            }
//
//
//
//        })
//
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {
        mPage++
        GlobalScope.launch(Dispatchers.IO) {
            val result = NetRepository.query(mPage, mSearchName)
            when(result){
                is HttpResult.Success -> {

                    val mList = mutableListOf<MainArticle>()
                    result.data?.run {
                        for (i in datas.indices){
                            val mainArticle = MainArticle()
                            mainArticle.link = datas[i].link
                            mainArticle.chapterName = datas[i].chapterName
                            mainArticle.niceShareDate = datas[i].niceShareDate
                            mainArticle.title = datas[i].title
                            mList.add(mainArticle)
                        }
                    }

                    withContext(Dispatchers.Main){
                        callback.onResult(mList,params.key)
                    }

                }

                else -> {

                }
            }
        }
//
//
//        NetManager.getInstance().query(mPage,mSearchName,object: BaseObserver<CollectArticleResponse>(){
//            override fun onRequestSuccess(t: CollectArticleResponse?) {
//                if (t!=null){
//                    val mList = mutableListOf<MainArticle>()
//                    for (i in t.datas.indices){
//                        t.datas[i].apkLink
//                        val mainArticle = MainArticle()
//                        mainArticle.link = t.datas[i].link
//                        mainArticle.chapterName = t.datas[i].chapterName
//                        mainArticle.niceShareDate = t.datas[i].niceShareDate
//                        mainArticle.title = t.datas[i].title
//                        mList.add(mainArticle)
//                    }
//                    callback.onResult(mList,params.key)
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
//
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {

    }
}