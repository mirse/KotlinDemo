package com.wdz.ktcommon.base

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 13:38

 */
abstract class BaseMvvmViewModel<M: BaseModel>: ViewModel() {

    protected var model: M? = null
    protected var lifecycleOwner: LifecycleOwner? = null
    protected val netRepository by lazy { NetRepository() }

    /**
     * http请求时的状态变化liveData
     */
    var httpLiveData: MutableLiveData<HttpRequestStatus> =
        MutableLiveData()

    protected abstract fun initModel(context: Context?)

//    /**
//     * 设置lifecycleOwner
//     * @param lifecycleOwner
//     */
//    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner?) {
//        this.lifecycleOwner = lifecycleOwner
//    }
//
//    fun getLifecycleOwner(): LifecycleOwner? {
//        return lifecycleOwner
//    }
//
//    fun getHttpLiveData(): MutableLiveData<HttpRequestStatus>? {
//        return httpLiveData
//    }
}