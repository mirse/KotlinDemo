package com.wdz.kotlindemo.main

import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.module_account.login.LoginModel
import com.wdz.module_account.login.LoginView

public class MainViewModel: BaseMvvmViewModel<MainView, MainModel>() {
    override fun initModel() {

    }
}