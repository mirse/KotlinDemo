package com.wdz.common.view.dialog;

public abstract class CommonDialogBuilder {
    /**
     * 设置标题
     * @param title
     * @return
     */
    public abstract CommonDialogBuilder setTitle(String title);

    /**
     * 设置右侧按钮文本
     * @param positiveButtonText 右侧文本
     * @param onClickListener 点击监听事件
     * @return
     */
    public abstract CommonDialogBuilder setPositiveButtonText(String positiveButtonText, OnClickListener onClickListener);

    /**
     * 设置左侧文本
     * @param negativeButtonText 左侧文本
     * @param onClickListener 点击事件
     * @return
     */
    public abstract CommonDialogBuilder setNegativeButtonText(String negativeButtonText, OnClickListener onClickListener);

    /**
     * 设置进出场动画
     * @param anim
     * @return
     */
    public abstract CommonDialogBuilder setAnim(int anim);

    /**
     * 构建CommonDialogFragment
     * @return
     */
    public abstract CommonDialogFragment bulid();

    /**
     * 显示CommonDialogFragment
     * @return
     */
    public abstract CommonDialogFragment show();

    /**
     * 常规dialog点击监听
     */
    public interface OnClickListener{
        void onClick(CommonDialogFragment commonDialogFragment);
    }

    /**
     * 输入dialog按钮确认点击监听
     */
    public interface OnClickEditListener{
        /**
         * @param content 输入框文字
         */
        void onClick(CommonDialogFragment commonDialogFragment, String content);
    }

}
