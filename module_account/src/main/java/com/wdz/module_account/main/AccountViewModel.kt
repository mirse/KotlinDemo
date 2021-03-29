package com.wdz.module_account.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.base.BaseResponse


public class AccountViewModel: BaseMvvmViewModel<AccountModel>() {

    public override fun initModel(context: Context) {
        model = AccountModel()
    }

    fun logoutUser(){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        model.logoutUser(object:AccountModel.LogoutListener{
            override fun logoutSuccess(response: BaseResponse.DataBean) {
                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS)

            }
            override fun logoutFail(errorMsg: String) {
                httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
            }
        })
    }

}