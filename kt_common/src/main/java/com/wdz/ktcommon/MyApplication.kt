package com.wdz.ktcommon

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData

import com.wdz.ktcommon.response.LoginResponse
import com.wdz.ktcommon.utils.getLoginUser
import kotlin.properties.Delegates

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:13

 */
class MyApplication: Application() {
    private var loginResponse: LoginResponse? = null
    private val loginResponseMutableLiveData =
        MutableLiveData<LoginResponse>()
    companion object{
        // TODO: 2021/3/31 延迟加载 lateinit->引用类型   Delegates.notNull->基本数据类型，引用类型（延迟初始化的委托类）
        var context:Context by Delegates.notNull()

    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun setUserInfo(loginResponse: LoginResponse) {
        this.loginResponse = loginResponse
        loginResponseMutableLiveData.postValue(loginResponse)
    }

    fun getUerInfo(): MutableLiveData<LoginResponse> {
        //如果loginResponse为null，重新从mmkv读取
        if (loginResponse == null) {
            val loginUser = getLoginUser()
            if (loginUser != null) {
                setUserInfo(loginUser)
            }
        }
        return loginResponseMutableLiveData
    }
}