package com.wdz.module_article.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wdz.main.main.paging.PositionCategoryDataSource
import com.wdz.module_article.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/3/19 16:57

 */
open class BaseDataSource(): PageKeyedDataSource<String, MainArticle>() {
    public var cid:Int = 0
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, MainArticle>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {

    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, MainArticle>
    ) {

    }

}