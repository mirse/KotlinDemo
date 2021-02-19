package com.wdz.main.main.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/19 9:53

 */
class SearchDataSourceFactory(searchName:String):DataSource.Factory<Int,MainArticle>() {

    private val mSearchName = searchName;

    private val mutableLiveData = MutableLiveData<PositionSearchDataSource>()
    override fun create(): DataSource<Int, MainArticle> {
        val positionArticleDataSource = PositionSearchDataSource(mSearchName)
        mutableLiveData.postValue(positionArticleDataSource)
        return positionArticleDataSource as DataSource<Int, MainArticle>
    }
}