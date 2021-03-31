package com.wdz.ktcommon.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**

 * @Author dezhi.wang

 * @Date 2021/3/26 10:54

 */
abstract class BaseKVmActivity: AppCompatActivity() {
    private val TAG = this::class.simpleName
    private val mColor = 0
    //private var mLoadingDialog: LoadingDialog ?= null
    private var compositeDisposable: CompositeDisposable? = null

    /**
     * 内联函数、懒加载 加载视图
     * 只有在isUseDataBinding == true时需要调用
     * @return Lazy<Any>
     */
    protected inline fun <reified T:ViewDataBinding> dataBinding(): Lazy<Any> = lazy{
        Log.i("BaseKVmActivity", "bind: ")
        //是否使用dataBinding
        if (isUseDataBinding())
            DataBindingUtil.setContentView<T>(this,getLayoutId()).apply {
                lifecycleOwner = this@BaseKVmActivity
             }
        else
            setContentView(getLayoutId())

    }

    /**
     * viewModel的定义和初始化
     * @return Lazy<V>
     */
    protected inline fun <reified V:ViewModel> getVm(): Lazy<V> = lazy{
        Log.i("BaseKVmActivity", "bind: ")
        ViewModelProvider(this@BaseKVmActivity).get(V::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentBar()
        initView()
        initData()
    }


    /**
     * 添加订阅
     * 配合RxView、RxTextView、RxCompoundButton相关
     * @param disposable
     */
    open fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(disposable)
    }

    /**
     * 取消所有订阅
     */
    open fun clearDisposable() {
        compositeDisposable?.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDisposable()
    }

//    /**
//     * 显示loading加载框
//     */
//    fun showLoading(){
//        if (mLoadingDialog == null){
//            mLoadingDialog = LoadingDialog(this)
//        }
//        mLoadingDialog?.show()
//    }
//    /**
//     * 隐藏loading加载框
//     */
//    fun hideLoading(){
//        mLoadingDialog?.dismiss()
//    }
    /**
     * 设置状态栏透明
     */
    private fun setTransparentBar() {
        if (isTransparentBar()) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (mColor == 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = Color.TRANSPARENT
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.statusBarColor = mColor
                }
            }

            //适配凹凸异形屏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val lp = getWindow().attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                getWindow().attributes = lp
            }
        }
    }

    /**
     * 状态栏是否透明
     * @return
     */
    abstract fun isTransparentBar(): Boolean

    /**
     * 是否使用databinding
     * @return
     */
    abstract fun isUseDataBinding(): Boolean

    /**
     * 设置ui视图
     * @return
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化contentView相关
     */
    abstract fun initView()

    /**
     * 初始化data相关
     */
    abstract fun initData()
}