package com.wdz.module_account.login

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse

import com.wdz.module_account.login.bean.RegisterStatus

public class LoginViewModel: BaseMvvmViewModel<LoginModel>(){
    private val TAG = this::class.simpleName
//    var loginStatus:MutableLiveData<LoginStatus> = MutableLiveData();
//    var mLoginStatus:LoginStatus = LoginStatus()
    var userName:String = ""
    var pwd:String = ""
    public override fun initModel(context: Context) {
        model = LoginModel()
    }

    fun login(v:View){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        model.login(userName,pwd,object :LoginModel.LoginListener{
            override fun loginSuccess(t: LoginResponse?) {
                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(t))
            }

            override fun loginFail(errorMsg: String) {
                httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
            }
        })
    }

    val userTextWatch = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            userName = p0.toString()
        }

    }

    val pwdWatch = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            pwd = p0.toString()
        }

    }

}