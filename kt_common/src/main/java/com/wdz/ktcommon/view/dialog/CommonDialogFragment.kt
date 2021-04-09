package com.wdz.ktcommon.view.dialog

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Guideline
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.wdz.ktcommon.R
import kotlinx.android.synthetic.main.dialog_fragment_common.*
import kotlinx.android.synthetic.main.dialog_fragment_edittext.*
import kotlinx.android.synthetic.main.dialog_fragment_image.*
import kotlinx.android.synthetic.main.dialog_fragment_message.tv_message
import kotlinx.android.synthetic.main.dialog_fragment_message.tv_title

/**

 * @Author dezhi.wang

 * @Date 2021/4/1 16:59

 */
class CommonDialogFragment: DialogFragment() {

    companion object{
        /**
         * 文本形式
         */
        const val DIALOG_TYPE_MESSAGE = 0

        /**
         * 输入框形式
         */
        const val DIALOG_TYPE_EDIT_TEXT = 1

        /**
         * 图片/文本形式
         */
        const val DIALOG_TYPE_IMAGE = 2
    }

    /**
     * 输入框文字
     */
    private var content = ""
    private var title = ""
    private var dialogType: Int = DIALOG_TYPE_MESSAGE
    private var message = ""
    private var hint = ""
    private var positiveButtonText = ""
    private var negativeButtonText = ""
    private var anim = 0
    private var icon = 0
    private var mPositiveButtonListener: CommonDialogBuilder.OnClickListener? =
        null
    private var mPositiveEditListener: CommonDialogBuilder.OnClickEditListener? =
        null
    private var mNegativeButtonListener: CommonDialogBuilder.OnClickListener? =
        null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_common, container, false)
        initView(view)
        dialog?.window?.attributes?.windowAnimations = anim
        return view
    }

    private fun initView(view: View) {
        val guideline = view.findViewById<Guideline>(R.id.guideline)
        val tv_cancel = view.findViewById<TextView>(R.id.tv_cancel)
        val tv_sure = view.findViewById<TextView>(R.id.tv_sure)
        val frameLayout = view.findViewById<FrameLayout>(R.id.frameLayout)

        if (negativeButtonText.isBlank()){
            guideline.setGuidelinePercent(0F)
            tv_cancel.visibility = View.GONE
        }
        else{
            guideline.setGuidelinePercent(0.5F)
            tv_cancel.text = negativeButtonText
        }
        tv_sure.text = positiveButtonText

        //点击确定按钮
        tv_sure.setOnClickListener {
            when(dialogType){
                DIALOG_TYPE_MESSAGE -> {
                    mPositiveButtonListener?.onClick(this)
                }
                DIALOG_TYPE_EDIT_TEXT -> {
                    mPositiveEditListener?.onClick(this,content)
                }
            }
        }
        //点击取消按钮
        tv_cancel.setOnClickListener {
            mNegativeButtonListener?.onClick(this)
            dismiss()
        }

        when(dialogType){
            DIALOG_TYPE_MESSAGE -> {
                val view = layoutInflater.inflate(R.layout.dialog_fragment_message, null)
                val tv_title = view.findViewById<TextView>(R.id.tv_title)
                val tv_message = view.findViewById<TextView>(R.id.tv_message)
                tv_title.text = title
                tv_message.text = message
                frameLayout.addView(view)
            }
            DIALOG_TYPE_EDIT_TEXT -> {
                val view = layoutInflater.inflate(R.layout.dialog_fragment_edittext, null)
                val tv_title = view.findViewById<TextView>(R.id.tv_title)
                val et_message = view.findViewById<EditText>(R.id.et_message)
                tv_title.text = title
                et_message.hint = hint
                tv_sure.isEnabled = false
                et_message.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun afterTextChanged(p0: Editable?) {
                        content = p0.toString().trim()
                        tv_sure.isEnabled = p0.toString().trim().isNotEmpty()
                    }

                })
                frameLayout.addView(view)
            }
            DIALOG_TYPE_IMAGE -> {
                val view = layoutInflater.inflate(R.layout.dialog_fragment_image, null)
                val tv_message = view.findViewById<TextView>(R.id.tv_message)
                val iv_icon = view.findViewById<ImageView>(R.id.iv_icon)
                tv_message.text = message
                iv_icon.setImageResource(icon)
                frameLayout.addView(view)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        context?.let {
            dialog?.window?.setLayout(dp2px(it,270),ViewGroup.LayoutParams.WRAP_CONTENT)
        }

    }

    private fun dp2px(context: Context, dipValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue*scale + 0.5).toInt()
    }


    /*---------------------------------------------------------------------------------------------------------*/

    /*---------------------------------------------------------------------------------------------------------*/
    /**
     * 消息类型dialog
     */
    class MessageDialogFragmentBuilder(private val mActivity: FragmentActivity) : CommonDialogBuilder() {
        private val commonDialogFragment: CommonDialogFragment = CommonDialogFragment()

        init {
            commonDialogFragment.dialogType = DIALOG_TYPE_MESSAGE
        }

        override fun setTitle(title: String): CommonDialogBuilder {
            commonDialogFragment.title = title
            return this
        }

        override fun setMessage(s: String): CommonDialogBuilder {
            commonDialogFragment.message = s
            return this
        }

        override fun setPositiveButtonText(
            positiveButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            commonDialogFragment.positiveButtonText = positiveButtonText
            commonDialogFragment.mPositiveButtonListener = onClickListener
            return this
        }

        override fun setNegativeButtonText(
            negativeButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            commonDialogFragment.negativeButtonText = negativeButtonText
            commonDialogFragment.mNegativeButtonListener = onClickListener
            return this
        }

        override fun setAnim(anim: Int): MessageDialogFragmentBuilder {
            commonDialogFragment.anim = anim
            return this
        }

        override fun build(): CommonDialogFragment {
            return commonDialogFragment
        }

        override fun show(): CommonDialogFragment {
            val dialogFragment: CommonDialogFragment = build()
            dialogFragment.show(mActivity.supportFragmentManager, "dialog")
            return dialogFragment
        }


    }


    /**
     * 输入类型dialog
     */
    class EditTextDialogFragmentBuilder(private val mActivity: FragmentActivity) : CommonDialogBuilder() {
        private val commonDialogFragment = CommonDialogFragment()

        init {
            commonDialogFragment.dialogType = DIALOG_TYPE_EDIT_TEXT
        }

        override fun setTitle(title: String): EditTextDialogFragmentBuilder {
            commonDialogFragment.title = title
            return this
        }
        override fun setMessage(s: String): EditTextDialogFragmentBuilder {
            commonDialogFragment.message = s
            return this
        }


        override fun setPositiveButtonText(
            positiveButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            return this
        }

        /**
         * 设置editText输入框提示语
         *
         * @param hint
         * @return
         */
        fun setHint(hint: String): EditTextDialogFragmentBuilder {
            commonDialogFragment.hint = hint
            return this
        }

        /**
         * 确认按钮相关，由于输入框类型点击确认需要返回文本，所有重写方法
         * @param positiveButtonText
         * @param onClickListener
         * @return
         */
        fun setPositiveButtonText(
            positiveButtonText: String,
            onClickListener: CommonDialogBuilder.OnClickEditListener?
        ): EditTextDialogFragmentBuilder {
            commonDialogFragment.positiveButtonText = positiveButtonText
            commonDialogFragment.mPositiveEditListener = onClickListener
            return this
        }

        override fun setNegativeButtonText(
            negativeButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            commonDialogFragment.negativeButtonText = negativeButtonText
            commonDialogFragment.mNegativeButtonListener = onClickListener
            return this
        }

        override fun setAnim(anim: Int): EditTextDialogFragmentBuilder {
            commonDialogFragment.anim = anim
            return this
        }

        override fun build(): CommonDialogFragment {
            return commonDialogFragment
        }

        override fun show(): CommonDialogFragment {
            val dialogFragment: CommonDialogFragment = build()
            dialogFragment.show(mActivity.supportFragmentManager, "dialog")
            return dialogFragment
        }



    }

    /**
     * 输入类型dialog
     */
    class ImageDialogFragmentBuilder(private val mActivity: FragmentActivity) : CommonDialogBuilder() {
        private val commonDialogFragment = CommonDialogFragment()

        init {
            commonDialogFragment.dialogType = DIALOG_TYPE_IMAGE
        }
        override fun setTitle(title: String): ImageDialogFragmentBuilder {
            return this
        }

        override fun setMessage(message: String): ImageDialogFragmentBuilder {
            commonDialogFragment.message = message
            return this
        }

        fun setImage(icon: Int): ImageDialogFragmentBuilder {
            commonDialogFragment.icon = icon
            return this
        }

        override fun setPositiveButtonText(
            positiveButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            commonDialogFragment.positiveButtonText = positiveButtonText
            commonDialogFragment.mPositiveButtonListener = onClickListener
            return this
        }

        override fun setNegativeButtonText(
            negativeButtonText: String,
            onClickListener: OnClickListener?
        ): CommonDialogBuilder {
            commonDialogFragment.negativeButtonText = negativeButtonText
            commonDialogFragment.mNegativeButtonListener = onClickListener
            return this
        }

        override fun setAnim(anim: Int): ImageDialogFragmentBuilder {
            commonDialogFragment.anim = anim
            return this
        }

        override fun build(): CommonDialogFragment {
            return commonDialogFragment
        }

        override fun show(): CommonDialogFragment {
            val dialogFragment: CommonDialogFragment = build()
            dialogFragment.show(mActivity.supportFragmentManager, "dialog")
            return dialogFragment
        }


    }
}