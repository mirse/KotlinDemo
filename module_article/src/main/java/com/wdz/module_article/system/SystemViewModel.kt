package com.wdz.module_article.system

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.TreeResponse

/**

 * @Author dezhi.wang

 * @Date 2021/3/8 14:46

 */
class SystemViewModel: BaseMvvmViewModel<SystemModel>() {
    val tree = MutableLiveData<List<TreeResponse>>()

    override fun initModel(context: Context?) {
        model = SystemModel()
    }


    fun getSystem(){
        model.getSystem(object : SystemModel.OnGetTreeListener{
            override fun onGetTreeSuccess(response: List<TreeResponse>) {
                tree.postValue(response)
            }

        })
    }
}