package com.wdz.module_article.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.wdz.module_article.bean.MainArticle
import kotlin.reflect.KClass

/**

 * @Author dezhi.wang

 * @Date 2021/3/19 17:00

 */
class BaseSourceFactory(private val cid:Int,private val pageKeyedDataSource: KClass<Any>): DataSource.Factory<Int, MainArticle>() {
    private val TAG = this::class.simpleName
    val mutableLiveData = MutableLiveData<PageKeyedDataSource<String, MainArticle>>()
    override fun create(): DataSource<Int, MainArticle> {

        val baseDataSource = pageKeyedDataSource.constructors.first().call(cid) as PageKeyedDataSource<String, MainArticle>

        mutableLiveData.postValue(baseDataSource)
        return baseDataSource as DataSource<Int, MainArticle>
    }
}