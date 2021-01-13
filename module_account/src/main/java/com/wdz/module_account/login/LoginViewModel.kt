package com.wdz.module_account.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.response.LoginResponse
import com.wdz.module_account.login.bean.LoginStatus

public class LoginViewModel: BaseMvvmViewModel<LoginModel>(){
    var loginStatus:MutableLiveData<LoginStatus> = MutableLiveData();
    var mLoginStatus:LoginStatus = LoginStatus()
    override fun initModel() {
        model = LoginModel()
    }

    fun login(userName:String,pwd:String){
        model.login(userName,pwd,object : LoginModel.LoginListener{
            override fun loginSuccess(t: LoginResponse?) {
                mLoginStatus.isLoginSuccess = true
                if (t != null) {
                    mLoginStatus.loginResponse = t
                }
                loginStatus.value = mLoginStatus

            }

            override fun loginFail(errorMsg: String) {
                mLoginStatus.isLoginSuccess = false
                mLoginStatus.errorMsg  = errorMsg;
                loginStatus.value = mLoginStatus
            }
        })
    }

}