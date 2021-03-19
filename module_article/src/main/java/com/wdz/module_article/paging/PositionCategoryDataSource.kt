package com.wdz.main.main.paging

import androidx.paging.PageKeyedDataSource
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.*

import com.wdz.module_article.bean.MainArticle

/*
* PositionalDataSource-> 适用于目标数据总数固定，通过特定的位置加载
* ItemKeyedDataSource -> 适用于目标数据的加载依赖特定条目，比如根据第N条的信息加载第N+1条的数据
* PageKeyedDataSource -> 适用于目标数据根据页信息请求数据的场景，即参数中包含next/previous等类似页数信息
*
*/
public class PositionCategoryDataSource(cid:Int): PageKeyedDataSource<String, MainArticle>(){


    private var mPage = 0
    private var mCid = cid
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, MainArticle>
    ) {
        NetManager.getInstance().getProjectInfo(mPage,mCid,object: BaseObserver<ProjectInfoResponse>(){

            override fun onRequestSuccess(t: ProjectInfoResponse?) {
                if (t!=null){
                    val mList = mutableListOf<MainArticle>()
                    for (i in t.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = t.datas[i].link
                        mainArticle.chapterName = t.datas[i].chapterName
                        mainArticle.niceShareDate = t.datas[i].niceShareDate
                        mainArticle.title = t.datas[i].title
                        mainArticle.envelopePic = t.datas[i].envelopePic
                        mainArticle.des = t.datas[i].desc
                        mList.add(mainArticle)
                    }
                    callback.onResult(mList,"before","after")
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }


        })
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {
        mPage++
        NetManager.getInstance().getProjectInfo(mPage,mCid,object: BaseObserver<ProjectInfoResponse>(){
            override fun onRequestSuccess(t: ProjectInfoResponse?) {
                if (t!=null){
                    val mList = mutableListOf<MainArticle>()
                    for (i in t.datas.indices){
                        val mainArticle = MainArticle()
                        mainArticle.link = t.datas[i].link
                        mainArticle.chapterName = t.datas[i].chapterName
                        mainArticle.niceShareDate = t.datas[i].niceShareDate
                        mainArticle.title = t.datas[i].title
                        mainArticle.envelopePic = t.datas[i].envelopePic
                        mList.add(mainArticle)
                    }
                    callback.onResult(mList,params.key)
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {

    }
}