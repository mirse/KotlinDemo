package com.wdz.kotlindemo.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.module_account.login.LoginModel


public class MainViewModel(application: Application) : BaseMvvmViewModel<MainModel>(application) {


    public override fun initModel(context: Context) {

    }

}