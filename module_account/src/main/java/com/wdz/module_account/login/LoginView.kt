package com.wdz.module_account.login

public interface LoginView {
    fun loginSuccess()
    fun loginFail(errorMsg:String)
}