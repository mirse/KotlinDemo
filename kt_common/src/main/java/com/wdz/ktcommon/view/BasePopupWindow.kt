package com.wdz.ktcommon.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow

/**
 * popupWindow基类
 */
abstract class BasePopupWindow(val context: Context) : PopupWindow(context) {
    private val TAG = this.javaClass.simpleName
    private var mView: View? = null

    init {
        initPopupView()
    }

    protected fun initPopupView() {
        mView = LayoutInflater.from(context).inflate(layoutId, null)

        this.contentView = mView
        this.animationStyle = popupAnimationStyle
        this.width = popupWidth
        this.height = popupHeight
        setBackgroundDrawable(backgroundDrawable)
        this.isFocusable = isPopupFocus
        mView?.getRootView()?.setOnTouchListener { v, event ->
            dismiss()
            true
        }
        initView()
        initData()
    }

    /**
     * 获取子View
     * @param viewId
     * @return
     */
    fun getItemView(viewId: Int): View? {
        return if (mView != null) {
            mView!!.findViewById(viewId)
        } else null
    }

    /**
     * 初始化视图
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 获取popWindow layoutId
     *
     * @return
     */
    abstract val layoutId: Int

    /**
     * 获取popWindow animation
     *
     * @return
     */
    abstract val popupAnimationStyle: Int

    /**
     * 获取popWindow width
     *
     * @return
     */
    abstract val popupWidth: Int

    /**
     * 获取popWindow height
     *
     * @return
     */
    abstract val popupHeight: Int

    /**
     * Popup是否有焦点
     *
     * @return
     */
    abstract val isPopupFocus: Boolean

    /**
     * 获取popWindow BackgroundDrawable
     *
     * @return
     */
    abstract val backgroundDrawable: ColorDrawable?


}