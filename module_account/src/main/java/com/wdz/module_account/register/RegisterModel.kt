package com.wdz.module_account.register

import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.response.LoginResponse
import com.wdz.module_account.login.LoginModel

public class RegisterModel: BaseModel() {

    fun register(userName: String, pwd: String,rePwd: String,registerListener: RegisterListener) {
        NetManager.getInstance().register(userName,pwd,rePwd,object : BaseObserver<BaseResponse.DataBean>() {

            override fun onRequestSuccess(t: BaseResponse.DataBean?) {
                registerListener.registerSuccess()
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {
                if (errorMsg != null) {
                    registerListener.registerFail(errorMsg)
                }
                else{
                    registerListener.registerFail("登录失败")
                }
            }

            override fun onRequestFailure(errorMsg: String?) {
                if (errorMsg != null) {
                    registerListener.registerFail(errorMsg)
                }
                else{
                    registerListener.registerFail("登录失败")
                }
            }




        })
    }


    fun login(userName: String, pwd: String,loginListener: LoginModel.LoginListener) {
        NetManager.getInstance().login(userName,pwd,object : BaseObserver<LoginResponse>() {
            override fun onRequestSuccess(t: LoginResponse?) {
                loginListener.loginSuccess(t)
            }

            override fun onRequestError(errorCode: Int, errorMsg: String?) {
                if (errorMsg != null) {
                    loginListener.loginFail(errorMsg)
                }
                else{
                    loginListener.loginFail("登录失败")
                }
            }

            override fun onRequestFailure(errorMsg: String?) {
                if (errorMsg != null) {
                    loginListener.loginFail(errorMsg)
                }
                else{
                    loginListener.loginFail("登录失败")
                }
            }


        })
    }

    interface RegisterListener{
        fun registerSuccess()
        fun registerFail(errorMsg:String)
    }
}