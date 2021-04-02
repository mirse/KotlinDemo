package com.wdz.module_account.main


import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.util.MmkvUtils
import com.wdz.ktcommon.base.BaseModel


public class AccountModel: BaseModel(){

    fun logoutUser(logoutListener: LogoutListener){
        NetManager.getInstance().logout(object: BaseObserver<BaseResponse.DataBean>() {
            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
                MmkvUtils.clearLoginUser()
                logoutListener.logoutSuccess(BaseResponse.DataBean())
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
        fun logoutSuccess(response:BaseResponse.DataBean)
        fun logoutFail(errorMsg:String)
    }
}