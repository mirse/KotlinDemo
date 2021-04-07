package com.wdz.module_account.login

import android.text.Editable
import android.text.TextWatcher

import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.LoginResponse
import com.wdz.common.util.MmkvUtils
import com.wdz.ktcommon.base.BaseModel

public class LoginModel: BaseModel() {





    fun login(userName: String, pwd: String,loginListener: LoginListener) {

    }





    interface LoginListener{
        fun loginSuccess(t:LoginResponse?)
        fun loginFail(errorMsg:String)
    }

}