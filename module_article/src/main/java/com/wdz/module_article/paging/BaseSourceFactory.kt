package com.wdz.module_article.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.wdz.main.main.paging.PositionCategoryDataSource
import com.wdz.module_article.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/3/19 17:00

 */
class BaseSourceFactory(cid:Int,private val dataSource: Class<Any>): DataSource.Factory<Int, MainArticle>() {
    private val mCid = cid
    val mutableLiveData = MutableLiveData<BaseDataSource>()
    override fun create(): DataSource<Int, MainArticle> {
        val javaClass = dataSource.javaClass
        val newInstance = javaClass.newInstance()
        val baseDataSource = newInstance as BaseDataSource
        baseDataSource.cid = mCid;
        //val positionArticleDataSource = BaseDataSource(mCid)
        mutableLiveData.postValue(baseDataSource)
        return baseDataSource as DataSource<Int, MainArticle>
    }
}