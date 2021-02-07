package com.wdz.module_account.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.bean.NetRequestStatus
import com.wdz.module_account.login.bean.RegisterStatus

public class AccountViewModel: BaseMvvmViewModel<AccountModel>() {
    var logoutStatus: MutableLiveData<NetRequestStatus<BaseResponse.DataBean>> = MutableLiveData()
    var mLogoutStatus:NetRequestStatus<BaseResponse.DataBean> = NetRequestStatus()
    override fun initModel(context: Context) {
        model = AccountModel()
    }

    fun logoutUser(){
        mLogoutStatus.requestStatus = HttpRequestStatus.REQUESTING
        logoutStatus.postValue(mLogoutStatus)
        model.logoutUser(object:AccountModel.LogoutListener{

            override fun logoutSuccess(response: BaseResponse.DataBean) {
                mLogoutStatus.requestStatus = HttpRequestStatus.REQUEST_SUCCESS
                logoutStatus.postValue(mLogoutStatus)
            }

            override fun logoutFail(errorMsg: String) {
                mLogoutStatus.requestStatus = HttpRequestStatus.REQUEST_FAIL
                mLogoutStatus.errorMsg = errorMsg
                logoutStatus.postValue(mLogoutStatus)
            }
        })
    }

}