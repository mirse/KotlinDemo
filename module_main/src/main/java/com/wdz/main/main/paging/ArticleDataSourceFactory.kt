package com.wdz.main.main.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/19 9:53

 */
class ArticleDataSourceFactory:DataSource.Factory<Int,MainArticle>() {

    val mutableLiveData = MutableLiveData<PositionArticleDataSource>()

    override fun create(): DataSource<Int, MainArticle> {
        val positionArticleDataSource = PositionArticleDataSource()
        mutableLiveData.postValue(positionArticleDataSource)
        return positionArticleDataSource
    }
}