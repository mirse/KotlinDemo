package com.wdz.module_account.main

import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel

public class AccountViewModel: BaseMvvmViewModel<AccountModel>() {
    override fun initModel() {
        model = AccountModel()
    }

    fun logoutUser(){
        model.logoutUser(object:AccountModel.LogoutListener{
            override fun logoutSuccess() {

            }

            override fun logoutFail(errorMsg: String) {

            }
        })
    }

}