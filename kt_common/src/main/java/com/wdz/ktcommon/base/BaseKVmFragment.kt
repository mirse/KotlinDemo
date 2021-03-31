package com.wdz.ktcommon.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wdz.ktcommon.view.LoadingDialog


/**

 * @Author dezhi.wang

 * @Date 2021/3/26 10:52

 */
abstract class BaseKVmFragment: Fragment() {

    var viewDataBinding:ViewDataBinding?=null
    private var mLoadingDialog: LoadingDialog?= null

    /**
     * loading开始时间
     */
    private var loadingStartTime: Long = 0

    /**
     * loading结束时间
     */
    private var loadingEndTime: Long = 0

    /**
     * viewModel的定义和初始化
     * @return Lazy<V>
     */
    protected inline fun <reified V: ViewModel> getVm(): Lazy<V> = lazy{
        ViewModelProvider(this).get(V::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        //是否使用dataBinding
        return if (isUseDataBinding()){
            viewDataBinding =
                DataBindingUtil.inflate<ViewDataBinding>(inflater, getLayoutId(), container, false).apply {
                    lifecycleOwner = this@BaseKVmFragment
                }
            viewDataBinding?.root
        } else{
            inflater.inflate(getLayoutId(), container, false)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
//        if (activity != null) {
//            vm.httpLiveData.observe(
//                activity,
//                object : Observer<HttpRequestStatus?> {
//                    fun onChanged(httpRequestStatus: HttpRequestStatus) {
//                        if (httpRequestStatus == HttpRequestStatus.REQUESTING) {
//                            showLoading()
//                        } else if (httpRequestStatus == HttpRequestStatus.REQUEST_SUCCESS || httpRequestStatus == HttpRequestStatus.REQUEST_FAIL) {
//                            hideLoading()
//                        }
//                    }
//                })
//        }
    }

    open fun showLoading() {
        loadingStartTime = System.currentTimeMillis()
        if (mLoadingDialog == null){
           mLoadingDialog = context?.let { LoadingDialog(it) }
        }
        mLoadingDialog?.show()
    }

    open fun hideLoading() {
        loadingEndTime = System.currentTimeMillis()
        val loadingDurationTime: Long = loadingEndTime - loadingStartTime
        if (loadingDurationTime < 500) {
            Handler().postDelayed({
                mLoadingDialog?.dismiss()
            }, 500 - loadingDurationTime)
        } else {
            mLoadingDialog?.dismiss()
        }
    }

    /**
     * 设置view.xml
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化某些参数
     */
    abstract fun init()

    /**
     * 加载视图
     */
    abstract fun initView()

    /**
     * 加载数据
     */
    abstract fun initData()

    /**
     * 是否使用databinding
     * @return
     */
    abstract fun isUseDataBinding(): Boolean
}