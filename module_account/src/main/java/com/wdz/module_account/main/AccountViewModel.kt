package com.wdz.module_account.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import kotlinx.coroutines.launch


public class AccountViewModel: BaseMvvmViewModel<AccountModel>() {

    public override fun initModel(context: Context?) {
        model = AccountModel()
    }

    fun logoutUser(){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        viewModelScope.launch {
            val result = netRepository.logout()
            when(result){
                is HttpResult.Success -> {
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS)
                }
                is HttpResult.Error -> {
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(result.exception.message))
                }
            }
        }

    }

}