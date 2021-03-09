package com.wdz.module_article.system

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.main.main.paging.TreeInfoSourceFactory
import com.wdz.module_article.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/3/9 9:08

 */
class TreeInfoViewModel: BaseMvvmViewModel<TreeInfoModel>() {
    var treeInfoList: LiveData<PagedList<MainArticle>> = MutableLiveData<PagedList<MainArticle>>()

    override fun initModel(context: Context?) {
        model = TreeInfoModel()
    }
    init {


    }

    fun getTreeInfo(cid:Int): LiveData<PagedList<MainArticle>> {
        val treeInfoSourceFactory = TreeInfoSourceFactory(cid)
        treeInfoSourceFactory.create()
        treeInfoList = LivePagedListBuilder<Int, MainArticle>(treeInfoSourceFactory, 20).build()
        return treeInfoList
    }

}