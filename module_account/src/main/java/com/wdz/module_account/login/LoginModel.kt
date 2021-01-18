package com.wdz.module_account.login

import android.text.Editable
import android.text.TextWatcher
import com.wdz.common.mvvm.BaseModel
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.LoginResponse
import com.wdz.common.util.MmkvUtils

public class LoginModel: BaseModel() {

//    单例
//    companion object{
//        @Volatile
//        private var instance:LoginModel? = null
//        fun getInstance():LoginModel{
//            if (instance == null){
//                synchronized(this){
//                    if (instance == null)
//                    {
//                        instance = LoginModel()
//                    }
//                }
//            }
//            return instance!!;
//        }
//    }



    fun login(userName: String, pwd: String,loginListener: LoginListener) {
        NetManager.getInstance().login(userName,pwd,object : BaseObserver<LoginResponse>() {
            override fun onRequestSuccess(t: LoginResponse?) {
                MmkvUtils.setLoginUser(t)
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





    interface LoginListener{
        fun loginSuccess(t:LoginResponse?)
        fun loginFail(errorMsg:String)
    }

}