package com.wdz.module_account.main

import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel

public class AccountViewModel: BaseMvvmViewModel<AccountModel>() {
    lateinit var model:AccountModel;
    override fun initModel() {
        model = AccountModel()
    }

}