package com.wdz.ktcommon.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.http.repository.NetRepository

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 13:38

 */
abstract class BaseMvvmViewModel<M: BaseModel>(application: Application) : AndroidViewModel(application) {

    protected var model: M? = null


    /**
     * http请求时的状态变化liveData
     */
    var httpLiveData: MutableLiveData<HttpRequestStatus> =
        MutableLiveData()

    protected abstract fun initModel(context: Context)


}