package com.wdz.ktcommon.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

import androidx.constraintlayout.widget.ConstraintLayout
import com.wdz.ktcommon.R
import com.wdz.ktcommon.utils.getStatusBarHeight
import kotlinx.android.synthetic.main.title_bar_common.view.*


/**
 * 通用titleBar，高度自适应，宽度match
 * @author wdz
 */
class CommonTitleBar : ConstraintLayout {
    private var onClickListener: OnClickListener? = null
    private var typedArray: TypedArray? = null

    constructor(context: Context):super(context){
        initView(context, null)
    }

    constructor(context: Context,attrs: AttributeSet):super(context,attrs){
        initView(context, attrs)
    }

    constructor(context: Context,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr){
        initView(context, attrs)
    }


    /**
     * 加载视图
     * @param context
     * @param attrs
     */
    private fun initView(context: Context, attrs: AttributeSet?) {
        initUi(context)
        initStyle(context, attrs)
    }

    /**
     * 初始化ui
     * @param context
     */
    private fun initUi(context: Context) {
        View.inflate(context, R.layout.title_bar_common, this)

        val params = cl_root.layoutParams
        params.height = dp2px(context, 44f) + getStatusBarHeight(context)
        params.width = LayoutParams.MATCH_PARENT
        cl_root.layoutParams = params
        iv_nav_back.setOnClickListener(View.OnClickListener {
            onClickListener?.onBackPress()
        })
        iv_nav_close.setOnClickListener(View.OnClickListener {
            onClickListener?.onClosePress()
        })
        iv_right.setOnClickListener(View.OnClickListener {
            onClickListener?.onSettingPress()
        })
        text_right.setOnClickListener(View.OnClickListener {
            onClickListener?.onClickRightText()
        })
    }

    /**
     * 加载标题style
     * @param context
     * @param attrs
     */
    private fun initStyle(
        context: Context,
        attrs: AttributeSet?
    ) {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar)

        typedArray?.run {
            //设置背景颜色
            if (hasValue(R.styleable.CommonTitleBar_background_color)){
                cl_root.setBackgroundColor(getColor(
                    R.styleable.CommonTitleBar_background_color,
                        0xFFFFFF))
            }

            if (hasValue(R.styleable.CommonTitleBar_title)) {
                setTitle()
            }

            //最左侧图标 -- back
            if (hasValue(R.styleable.CommonTitleBar_icon_back)) {
                iv_nav_back.setImageResource(getResourceId(
                    R.styleable.CommonTitleBar_icon_back, R.mipmap.ic_return))
            }

            //左二图标 -- 预留
            if (hasValue(R.styleable.CommonTitleBar_icon_left)) {
                iv_left.setImageResource(
                    getResourceId(R.styleable.CommonTitleBar_icon_left, R.mipmap.ic_return))
            }

            //最右侧图标 -- close
            if (hasValue(R.styleable.CommonTitleBar_icon_right)) {
                iv_right.setImageResource(
                    getResourceId(R.styleable.CommonTitleBar_icon_right, R.mipmap.ic_add))
            }

            //右二图标 -- 一般为设置图标...
            if (hasValue(R.styleable.CommonTitleBar_icon_close)) {
                iv_nav_close.setImageResource(
                    getResourceId(R.styleable.CommonTitleBar_icon_close, R.mipmap.ic_add)
                )
            }

            //右侧文字
            if (hasValue(R.styleable.CommonTitleBar_text_right)) {
                text_right.text = getString(R.styleable.CommonTitleBar_text_right)
            }
        }

    }

    /**
     * 设置标题相关文字、颜色、大小、样式
     */
    private fun setTitle() {
        typedArray?.run {
            tv_title.text = getString(R.styleable.CommonTitleBar_title)
            if (hasValue(R.styleable.CommonTitleBar_title_size)) {
                tv_title.textSize = getDimensionPixelSize(
                    R.styleable.CommonTitleBar_title_size,
                    sp2px(context, 18f)
                ).toFloat()
            }
            if (hasValue(R.styleable.CommonTitleBar_title_color)) {
                tv_title.setTextColor(
                    getColor(
                        R.styleable.CommonTitleBar_title_color,
                        0x333333
                    )
                )
            }
            if (hasValue(R.styleable.CommonTitleBar_title_style)) {
                //只有常规与加粗两种
                if (getInt(R.styleable.CommonTitleBar_title_style, 0) == 0) {
                    tv_title.typeface = Typeface.DEFAULT
                } else if (getInt(R.styleable.CommonTitleBar_title_style, 0) == 1) {
                    tv_title!!.typeface = Typeface.DEFAULT_BOLD
                }
            }
        }

    }

    /**
     * 获取bar高度
     * @return
     */
    val barHeight: Int
        get() = cl_root.height

    /**
     * 设置标题名称
     * @param titleName
     */
    fun setTitleName(titleName: String?) {
        tv_title.text = titleName
    }

    /**
     * 设置TitleBar的点击事件
     * @param onClickListener
     */
    fun setOnTitleBarClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    /**
     * 点击监听
     */
    interface OnClickListener {
        /**
         * 点击返回按钮
         */
        fun onBackPress()

        /**
         * 点击关闭按钮
         */
        fun onClosePress()

        /**
         * 点击设置按钮
         */
        fun onSettingPress()

        /**
         * 点击右侧文本
         */
        fun onClickRightText()
    }

    /**
     * sp -> px
     *
     * @param context
     * @param spValue
     * @return
     */
    private fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * dp->px
     * @param context  context
     * @param dipValue dp值
     * @return px值
     */
    private fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }


}