package com.wdz.module_account.login

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse
import com.wdz.module_account.login.bean.LoginStatus
import com.wdz.module_account.login.bean.RegisterStatus

public class LoginViewModel: BaseMvvmViewModel<LoginModel>(){
    private val TAG = this::class.simpleName
    var loginStatus:MutableLiveData<LoginStatus> = MutableLiveData();
    var mLoginStatus:LoginStatus = LoginStatus()
    var userName:String = ""
    var pwd:String = ""
    override fun initModel(context: Context) {
        model = LoginModel()
    }

    fun login(v:View){
        mLoginStatus.requestStatus = HttpRequestStatus.REQUESTING
        loginStatus.postValue(mLoginStatus)
        model.login(userName,pwd,object :LoginModel.LoginListener{
            override fun loginSuccess(t: LoginResponse?) {
                mLoginStatus.requestStatus = HttpRequestStatus.REQUEST_SUCCESS
                if (t!=null){
                    mLoginStatus.loginResponse = t
                }
                loginStatus.postValue(mLoginStatus)
            }

            override fun loginFail(errorMsg: String) {
                mLoginStatus.requestStatus = HttpRequestStatus.REQUEST_FAIL
                mLoginStatus.errorMsg = errorMsg
                loginStatus.postValue(mLoginStatus)
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