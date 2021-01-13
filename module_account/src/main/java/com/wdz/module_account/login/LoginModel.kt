package com.wdz.module_account.login

import android.text.Editable
import android.text.TextWatcher
import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.LoginResponse

public class LoginModel: BaseModel() {
    fun login(userName: String, pwd: String,loginListener: LoginListener) {
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


    val userTextWatch = object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }

    val pwdWatch = object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }


    interface LoginListener{
        fun loginSuccess(t:LoginResponse?)
        fun loginFail(errorMsg:String)
    }

}