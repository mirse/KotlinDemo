package com.wdz.main.main.paging

import androidx.paging.PositionalDataSource
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.main.bean.MainArticle

public class PositionArticleDataSource:PositionalDataSource<MainArticle>(){
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MainArticle>) {

    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<MainArticle>
    ) {
        NetManager.getInstance().getArticle(0,object:BaseObserver<MainListResponse>(){
            override fun onRequestSuccess(t: MainListResponse?) {
                if (t!=null){
                    for (i in t.datas.indices){

                    }
                }
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {

            }

            override fun onRequestFailure(errorMsg: String?) {

            }

        })
    }
}