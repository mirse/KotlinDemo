package com.wdz.module_account.login

import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel

public class LoginViewModel: BaseMvvmViewModel<LoginView,LoginModel>(),LoginView,
    LoginModel.LoginListener {
    override fun initModel() {
        model = LoginModel()
    }

    fun login(userName:String,pwd:String){
        model.login(userName,pwd,this)
    }

    override fun loginSuccess() {
        if (view!=null){
            view.loginSuccess()
        }
    }

    override fun loginFail(errorMsg:String) {
        if (view!=null){
            view.loginFail(errorMsg)
        }
    }
}