package com.wdz.module_account.main

import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse

public class AccountModel:BaseModel(){

    fun logoutUser(logoutListener: LogoutListener){
        NetManager.getInstance().logout(object: BaseObserver<BaseResponse.DataBean>() {
            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
                logoutListener.logoutSuccess()
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {
                if (errorMsg != null) {
                    logoutListener.logoutFail(errorMsg)
                }
                else{
                    logoutListener.logoutFail("账号退出失败")
                }
            }

            override fun onRequestFailure(errorMsg: String?) {
                if (errorMsg != null) {
                    logoutListener.logoutFail(errorMsg)
                }
                else{
                    logoutListener.logoutFail("账号退出失败")
                }
            }

        })
    }

    interface LogoutListener{
        fun logoutSuccess()
        fun logoutFail(errorMsg:String)
    }
}