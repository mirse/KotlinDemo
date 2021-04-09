package com.wdz.module_account.login

import android.app.Application
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.wdz.ktcommon.base.BaseMvvmViewModel
import com.wdz.ktcommon.base.HttpResult
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository
import com.wdz.ktcommon.utils.setLoginUser

import kotlinx.coroutines.launch

public class LoginViewModel(application: Application) : BaseMvvmViewModel<LoginModel>(application){
    private val TAG = this::class.simpleName

    var userName:String = ""
    var pwd:String = ""
    public override fun initModel(context: Context) {
        model = LoginModel()
    }

    fun login(v:View){
        httpLiveData.postValue(HttpRequestStatus.REQUESTING)
        viewModelScope.launch {
            val result = NetRepository.login(userName, pwd)
            when(result){
                is HttpResult.Success -> {
                    setLoginUser(result.data)
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_SUCCESS.setMsg(result.data))
                }
                is HttpResult.Error -> {
                    val errorMsg = (result as HttpResult.Error).exception.message
                    httpLiveData.postValue(HttpRequestStatus.REQUEST_FAIL.setMsg(errorMsg))
                }
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


}