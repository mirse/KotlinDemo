package com.wdz.module_account.register


import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.response.LoginResponse
import com.wdz.common.util.MmkvUtils
import com.wdz.ktcommon.base.BaseModel
import com.wdz.module_account.login.LoginModel

public class RegisterModel: BaseModel() {

    fun register(userName: String, pwd: String,rePwd: String,registerListener: RegisterListener) {

    }


    fun login(userName: String, pwd: String,loginListener: LoginModel.LoginListener) {

    }

    interface RegisterListener{
        fun registerSuccess()
        fun registerFail(errorMsg:String)
    }
}