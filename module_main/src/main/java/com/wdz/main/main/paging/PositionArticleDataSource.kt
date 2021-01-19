package com.wdz.main.main.paging

import androidx.paging.PositionalDataSource
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle

public class PositionArticleDataSource:PositionalDataSource<MainArticle>(){
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MainArticle>) {
        NetManager.getInstance().getArticle(params.startPosition,object:BaseObserver<MainListResponse>(){
            override fun onRequestSuccess(t: MainListResponse?) {
                if (t!=null){
                    val mList = mutableListOf<MainArticle>()
                    for (i in t.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = t.datas[i].link
                        mainArticle.chapterName = t.datas[i].chapterName
                        mainArticle.niceShareDate = t.datas[i].niceShareDate
                        mainArticle.title = t.datas[i].title
                        mList.add(mainArticle)
                    }
                    callback.onResult(mList)
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<MainArticle>
    ) {
        NetManager.getInstance().getArticle(0,object:BaseObserver<MainListResponse>(){
            override fun onRequestSuccess(t: MainListResponse?) {
                if (t!=null){
                    val mList = mutableListOf<MainArticle>()
                    for (i in t.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = t.datas[i].link
                        mainArticle.chapterName = t.datas[i].chapterName
                        mainArticle.niceShareDate = t.datas[i].niceShareDate
                        mainArticle.title = t.datas[i].title
                        mList.add(mainArticle)
                    }
                    callback.onResult(mList,0,mList.size)
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }
}