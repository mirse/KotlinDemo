package com.wdz.ktcommon.view.dialog

abstract class CommonDialogBuilder {
    /**
     * 设置标题
     * @param title
     * @return
     */
    abstract fun setTitle(title: String): CommonDialogBuilder

    /**
     * 设置右侧按钮文本
     * @param positiveButtonText 右侧文本
     * @param onClickListener 点击监听事件
     * @return
     */
    abstract fun setPositiveButtonText(
        positiveButtonText: String,
        onClickListener: OnClickListener?
    ): CommonDialogBuilder

    /**
     * 设置左侧文本
     * @param negativeButtonText 左侧文本
     * @param onClickListener 点击事件
     * @return
     */
    abstract fun setNegativeButtonText(
        negativeButtonText: String,
        onClickListener: OnClickListener?
    ): CommonDialogBuilder

    /**
     * 设置进出场动画
     * @param anim
     * @return
     */
    abstract fun setAnim(anim: Int): CommonDialogBuilder

    /**
     * 构建CommonDialogFragment
     * @return
     */
    abstract fun build(): CommonDialogFragment

    /**
     * 显示CommonDialogFragment
     * @return
     */
    abstract fun show(): CommonDialogFragment

    /**
     * 常规dialog点击监听
     */
    interface OnClickListener {
        fun onClick(commonDialogFragment: CommonDialogFragment?)
    }

    /**
     * 输入dialog按钮确认点击监听
     */
    interface OnClickEditListener {
        /**
         * @param content 输入框文字
         */
        fun onClick(
            commonDialogFragment: CommonDialogFragment?,
            content: String?
        )
    }
}