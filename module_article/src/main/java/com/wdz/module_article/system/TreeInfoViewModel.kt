package com.wdz.module_article.system

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.ProjectResponse
import com.wdz.common.net.response.WxResponse
import com.wdz.main.main.paging.PositionCategoryDataSource

import com.wdz.main.main.paging.PositionTreeInfoDataSource
import com.wdz.main.main.paging.PositionWxArticleDataSource

import com.wdz.module_article.bean.MainArticle
import com.wdz.module_article.paging.BaseSourceFactory
import kotlin.reflect.KClass

/**

 * @Author dezhi.wang

 * @Date 2021/3/9 9:08

 */
class TreeInfoViewModel: BaseMvvmViewModel<TreeInfoModel>() {
    var treeInfoList: LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()
    var wxList = MutableLiveData<List<WxResponse>>()
    var categoryList = MutableLiveData<List<ProjectResponse>>()
    override fun initModel(context: Context?) {
        model = TreeInfoModel()
    }
    init {


    }

    fun getTreeInfo(cid:Int): LiveData<PagedList<MainArticle>> {
        val treeInfoSourceFactory = BaseSourceFactory(cid,(PositionTreeInfoDataSource::class as KClass<Any>))
        treeInfoSourceFactory.create()
        treeInfoList = LivePagedListBuilder<Int, MainArticle>(treeInfoSourceFactory, 20).build()
        return treeInfoList
    }
    /*
    * 获取微信文章
    */
    fun getVxArticle(cid:Int): LiveData<PagedList<MainArticle>> {
        val wxArticleSourceFactory = BaseSourceFactory(cid,(PositionWxArticleDataSource::class as KClass<Any>))
        wxArticleSourceFactory.create()
        treeInfoList = LivePagedListBuilder<Int, MainArticle>(wxArticleSourceFactory, 20).build()
        return treeInfoList
    }
    /*
    * 获取微信作者列表
    */
    fun getWx(): MutableLiveData<List<WxResponse>> {
        model.getArticle(object :TreeInfoModel.OnGetWxListener{
            override fun onGetWxSuccess(response: List<WxResponse>) {
                wxList.postValue(response)
            }
        })
        return wxList
    }

    /*
    * 获取项目分类列表
    */
    fun getCategory(): MutableLiveData<List<ProjectResponse>> {
        model.getCategory(object :TreeInfoModel.OnCategoryListener{
            override fun onCategorySuccess(response: List<ProjectResponse>) {
                categoryList.postValue(response)
            }

        })
        return categoryList
    }

    /*
    * 获取项目列表数据
    */
    fun getCategoryInfo(cid:Int): LiveData<PagedList<MainArticle>> {
//        val categorySourceFactory = CategorySourceFactory(cid)
        // TODO: 2021/3/23 kotlin转换类型
        val categorySourceFactory = BaseSourceFactory(cid,(PositionCategoryDataSource::class as KClass<Any>))
        categorySourceFactory.create()
        treeInfoList = LivePagedListBuilder<Int, MainArticle>(categorySourceFactory, 20).build()
        return treeInfoList
    }

}