package com.wdz.module_account.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel

public class LoginViewModel: BaseMvvmViewModel<LoginView,LoginModel>(){
    var loginStatus:MutableLiveData<Boolean> = MutableLiveData();
    override fun initModel() {
        model = LoginModel()
        loginStatus.value = false
    }

    fun login(userName:String,pwd:String){
        model.login(userName,pwd,object : LoginModel.LoginListener{
            override fun loginSuccess() {
                loginStatus.value = true
            }

            override fun loginFail(errorMsg: String) {
                loginStatus.value = false
            }
        })
    }

}