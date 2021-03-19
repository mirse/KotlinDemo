package com.wdz.main.main.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/19 9:53

 */
class WxArticleSourceFactory(cid:Int):DataSource.Factory<Int, MainArticle>() {
    private val mCid = cid
    val mutableLiveData = MutableLiveData<PositionWxArticleDataSource>()
    override fun create(): DataSource<Int, MainArticle> {
        val positionArticleDataSource = PositionWxArticleDataSource(mCid)
        mutableLiveData.postValue(positionArticleDataSource)
        return positionArticleDataSource as DataSource<Int, MainArticle>
    }
}