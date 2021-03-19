package com.wdz.main.main.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.bean.MainArticle
import com.wdz.module_article.paging.BaseDataSource

/**

 * @Author dezhi.wang

 * @Date 2021/1/19 9:53

 */
class CategorySourceFactory():BaseDataSource() {
    val mutableLiveData = MutableLiveData<PositionCategoryDataSource>()
    fun create(): DataSource<Int, MainArticle> {
        val positionArticleDataSource = PositionCategoryDataSource(cid)
        mutableLiveData.postValue(positionArticleDataSource)
        return positionArticleDataSource as DataSource<Int, MainArticle>
    }
}