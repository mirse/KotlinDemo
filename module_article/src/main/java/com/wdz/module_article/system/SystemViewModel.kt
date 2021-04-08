package com.wdz.module_article.system

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.wdz.ktcommon.base.BaseMvvmViewModel

import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.response.TreeResponse
import kotlinx.coroutines.launch

/**

 * @Author dezhi.wang

 * @Date 2021/3/8 14:46

 */
class SystemViewModel: BaseMvvmViewModel<SystemModel>() {
    val tree = MutableLiveData<List<TreeResponse>>()

    public override fun initModel(context: Context) {
        model = SystemModel()
    }


    fun getSystem(){
        viewModelScope.launch {
            val result = NetRepository.getTree()
            when(result){
                is HttpResult.Success ->{
                    tree.postValue(result.data)
                }
                is HttpResult.Error ->{

                }
            }
        }
//        model.getSystem(object : SystemModel.OnGetTreeListener{
//            override fun onGetTreeSuccess(response: List<TreeResponse>) {
//                tree.postValue(response)
//            }
//
//        })
    }
}