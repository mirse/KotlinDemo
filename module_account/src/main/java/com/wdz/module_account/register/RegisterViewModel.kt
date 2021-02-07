package com.wdz.module_account.register

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.common.mvvm.BaseMvvmViewModel
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.response.LoginResponse
import com.wdz.module_account.login.LoginModel
import com.wdz.module_account.login.bean.RegisterStatus

public class RegisterViewModel: BaseMvvmViewModel<RegisterModel>() {
    var registerStatus: MutableLiveData<RegisterStatus> = MutableLiveData();
    var mRegisterStatus:RegisterStatus = RegisterStatus()

    var userName:String = ""
    var pwd:String = ""
    var rePwd:String = ""
    var isLoginNext:Boolean = false
    override fun initModel(context: Context) {
        model = RegisterModel()
    }
    fun register(v: View){
        mRegisterStatus.requestStatus = HttpRequestStatus.REQUESTING
        registerStatus.postValue(mRegisterStatus)
        model.register(userName,pwd,rePwd,object : RegisterModel.RegisterListener{
            override fun registerSuccess() {
                //注册成功，继续登录
                if (isLoginNext){
                   loginNext()
                }
                else{
                    mRegisterStatus.requestStatus = HttpRequestStatus.REQUEST_SUCCESS
                    registerStatus.postValue(mRegisterStatus)
                }
            }

            override fun registerFail(errorMsg: String) {
                mRegisterStatus.requestStatus = HttpRequestStatus.REQUEST_FAIL
                mRegisterStatus.errorMsg = errorMsg
                registerStatus.postValue(mRegisterStatus)
            }
        })
    }

    /*
    * 注册成功直接登录账号
    */
    private fun loginNext() {
        model.login(userName,pwd,object:LoginModel.LoginListener{
            override fun loginSuccess(t: LoginResponse?) {
                mRegisterStatus.isLogin = true
                mRegisterStatus.requestStatus = HttpRequestStatus.REQUEST_SUCCESS
                if (t != null) {
                    mRegisterStatus.loginResponse = t
                }
                registerStatus.postValue(mRegisterStatus)
            }

            override fun loginFail(errorMsg: String) {
                mRegisterStatus.isLogin = true
                mRegisterStatus.loginErrorMsg = errorMsg;
                mRegisterStatus.requestStatus = HttpRequestStatus.REQUEST_FAIL
                registerStatus.postValue(mRegisterStatus)
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
    val rePwdWatch = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            rePwd = p0.toString()
        }

    }

    val checkChangeListener = object :CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            isLoginNext =p1
        }

    }



}