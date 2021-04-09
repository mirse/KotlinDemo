package com.wdz.module_account.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope



import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.utils.clearLoginUser
import kotlinx.coroutines.launch


public class AccountViewModel(application: Application) : BaseMvvmViewModel<AccountModel>(
    application
) {

    public override fun initModel(context: Context) {
        model = AccountModel()
    }

    fun logoutUser(){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
//        model?.logoutUser(object : AccountModel.LogoutListener{
//            override fun logoutSuccess(response: BaseResponse.DataBean) {
//
//            }
//
//            override fun logoutFail(errorMsg: String) {
//
//            }
//
//        })
        viewModelScope.launch {
            val result = NetRepository.logout()
            when(result){
                is HttpResult.Success -> {
                    clearLoginUser()
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS)
                }
                is HttpResult.Error -> {
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(result.exception.message))
                }
            }
        }

    }

}