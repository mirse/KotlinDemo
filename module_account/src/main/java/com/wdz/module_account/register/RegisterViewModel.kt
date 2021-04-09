package com.wdz.module_account.register

import android.app.Application
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope



import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.utils.setLoginUser
import com.wdz.module_account.login.LoginModel

import kotlinx.coroutines.launch

public class RegisterViewModel(application: Application) : BaseMvvmViewModel<RegisterModel>(
    application
) {


    var userName:String = ""
    var pwd:String = ""
    var rePwd:String = ""
    var isLoginNext:Boolean = false
    public override fun initModel(context: Context) {
        model = RegisterModel()
    }
    fun register(v: View){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        viewModelScope.launch {
            val result = NetRepository.register(userName,pwd,rePwd)
            when(result){
                is HttpResult.Success -> {
                    //注册成功，继续登录
                    if (isLoginNext){
                        loginNext()
                    }
                    else{
                        httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg("注册成功"))
                    }
                }
                is HttpResult.Error -> {
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg("注册失败：${result.exception.message}"))
                }
            }
        }

    }

    /*
    * 注册成功直接登录账号
    */
    private suspend fun loginNext() {
        val result = NetRepository.login(userName, pwd)
        when(result){
            is HttpResult.Success -> {
                setLoginUser(result.data)
                httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg("登录成功"))
            }
            is HttpResult.Error -> {
                httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg("登录失败：${result.exception.message}"))
            }
        }
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