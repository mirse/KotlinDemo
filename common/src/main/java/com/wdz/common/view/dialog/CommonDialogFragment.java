package com.wdz.common.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;


import com.wdz.common.R;
import com.wdz.common.R2;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author dezhi.wang
 */
public class CommonDialogFragment extends DialogFragment {
    private final String TAG = this.getClass().getSimpleName();


    /**
     * 文本形式
     */
    public static int DIALOG_TYPE_MESSAGE = 0;
    /**
     * 输入框形式
     */
    public static int DIALOG_TYPE_EDIT_TEXT = 1;

    /**
     * 图片/文本形式
     */
    public static int DIALOG_TYPE_IMAGE = 2;

    @BindView(R2.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R2.id.tv_cancel)
    TextView btnCancel;
    @BindView(R2.id.tv_sure)
    TextView btnSure;
    @BindView(R2.id.guideline)
    Guideline guideline;

    private String title = "";
    private int dialogType = DIALOG_TYPE_MESSAGE;
    private String message = "";
    private String hint = "";
    private String positiveButtonText = "";
    private String negativeButtonText = "";
    private int anim = 0;
    private int icon = 0;
    private CommonDialogBuilder.OnClickListener mPositiveButtonListener;
    private CommonDialogBuilder.OnClickEditListener mPositiveEditListener;
    private CommonDialogBuilder.OnClickListener mNegativeButtonListener;


    /**
     * 输入框文字
     */
    private String content = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateDialog: ");
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.dialog_fragment_common, container, false);
        ButterKnife.bind(this, view);
        initView();
        if (getDialog()!=null){
            if (getDialog().getWindow()!=null){
                if (anim!=0){
                    getDialog().getWindow().getAttributes().windowAnimations = anim;
                }

            }

        }

        return view;
    }

    /**
     * 初始化ui
     */
    private void initView() {

        //当左侧文本为空或空字符串则不显示左侧按钮
        if ("".equals(negativeButtonText) || null == negativeButtonText) {
            guideline.setGuidelinePercent(0);
            btnCancel.setVisibility(View.GONE);
        } else {
            guideline.setGuidelinePercent((float) 0.5);
            btnCancel.setText(negativeButtonText);
        }
        btnSure.setText(positiveButtonText);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNegativeButtonListener != null) {
                    mNegativeButtonListener.onClick(CommonDialogFragment.this);
                }
                dismiss();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogType == DIALOG_TYPE_MESSAGE) {
                    if (mPositiveButtonListener != null) {
                        mPositiveButtonListener.onClick(CommonDialogFragment.this);
                    }
                }
                else if (dialogType == DIALOG_TYPE_EDIT_TEXT){
                    if (mPositiveEditListener != null) {
                        mPositiveEditListener.onClick(CommonDialogFragment.this,content);
                    }
                }

            }
        });
        if (dialogType == DIALOG_TYPE_MESSAGE) {
            View childView = getLayoutInflater().inflate(R.layout.dialog_fragment_message, null);
            TextView tvTitle = childView.findViewById(R.id.tv_title);
            TextView tvMessage = childView.findViewById(R.id.tv_message);
            tvMessage.setText(message);
            tvTitle.setText(title);
            frameLayout.addView(childView);
        }
        else if (dialogType == DIALOG_TYPE_EDIT_TEXT) {
            View childView = getLayoutInflater().inflate(R.layout.dialog_fragment_edittext, null);
            EditText etMessage = childView.findViewById(R.id.et_message);
            TextView tvTitle = childView.findViewById(R.id.tv_title);
            tvTitle.setText(title);
            etMessage.setHint(hint);
            btnSure.setEnabled(false);
            etMessage.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    content = s.toString().trim();
                    int length = content.length();
                    if (length > 0) {
                        btnSure.setEnabled(true);
                    } else {
                        btnSure.setEnabled(false);
                    }
                }
            });
            frameLayout.addView(childView);
        }
        else if (dialogType == DIALOG_TYPE_IMAGE){
            View childView = getLayoutInflater().inflate(R.layout.dialog_fragment_image, null);
            TextView tvMessage = childView.findViewById(R.id.tv_message);
            ImageView ivIcon = childView.findViewById(R.id.iv_icon);
            tvMessage.setText(message);
            ivIcon.setImageResource(icon);
            frameLayout.addView(childView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            if (getDialog().getWindow() != null) {
                getDialog().getWindow().setLayout(dp2px(getContext(), 270), ViewGroup.LayoutParams.WRAP_CONTENT);
            }

        }

    }

    /**
     * dp->px
     */
    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /*---------------------------------------------------------------------------------------------------------*/

    /**
     * 消息类型dialog
     */
    public static class MessageDialogFragmentBuilder extends CommonDialogBuilder {
        private final CommonDialogFragment commonDialogFragment;
        private final FragmentActivity mActivity;

        public MessageDialogFragmentBuilder(FragmentActivity activity) {
            mActivity = activity;
            commonDialogFragment = new CommonDialogFragment();
            commonDialogFragment.dialogType = DIALOG_TYPE_MESSAGE;
        }

        @Override
        public MessageDialogFragmentBuilder setTitle(String title) {
            commonDialogFragment.title = title;
            return this;
        }

        public MessageDialogFragmentBuilder setMessage(String message) {
            commonDialogFragment.message = message;
            return this;
        }

        @Override
        public MessageDialogFragmentBuilder setPositiveButtonText(String positiveButtonText, OnClickListener onClickListener) {
            commonDialogFragment.positiveButtonText = positiveButtonText;
            commonDialogFragment.mPositiveButtonListener = onClickListener;
            return this;
        }

        @Override
        public MessageDialogFragmentBuilder setNegativeButtonText(String negativeButtonText, OnClickListener onClickListener) {
            commonDialogFragment.negativeButtonText = negativeButtonText;
            commonDialogFragment.mNegativeButtonListener = onClickListener;
            return this;
        }

        @Override
        public MessageDialogFragmentBuilder setAnim(int anim) {
            commonDialogFragment.anim = anim;
            return this;
        }

        @Override
        public CommonDialogFragment bulid() {
            return commonDialogFragment;
        }

        @Override
        public CommonDialogFragment show() {
            CommonDialogFragment dialogFragment = bulid();
            dialogFragment.show(mActivity.getSupportFragmentManager(), "dialog");
            return dialogFragment;
        }
    }


    /**
     * 输入类型dialog
     */
    public static class EditTextDialogFragmentBuilder extends CommonDialogBuilder {

        private final FragmentActivity mActivity;
        private final CommonDialogFragment commonDialogFragment;

        public EditTextDialogFragmentBuilder(FragmentActivity activity) {
            mActivity = activity;
            commonDialogFragment = new CommonDialogFragment();
            commonDialogFragment.dialogType = DIALOG_TYPE_EDIT_TEXT;
        }

        @Override
        public EditTextDialogFragmentBuilder setTitle(String title) {
            commonDialogFragment.title = title;
            return this;
        }

        @Override
        public EditTextDialogFragmentBuilder setPositiveButtonText(String positiveButtonText, OnClickListener onClickListener) {
            return this;
        }

        /**
         * 设置editText输入框提示语
         *
         * @param hint
         * @return
         */
        public EditTextDialogFragmentBuilder setHint(String hint) {
            commonDialogFragment.hint = hint;
            return this;
        }

        /**
         * 确认按钮相关，由于输入框类型点击确认需要返回文本，所有重写方法
         * @param positiveButtonText
         * @param onClickListener
         * @return
         */
        public EditTextDialogFragmentBuilder setPositiveButtonText(String positiveButtonText, OnClickEditListener onClickListener) {
            commonDialogFragment.positiveButtonText = positiveButtonText;
            commonDialogFragment.mPositiveEditListener = onClickListener;
            return this;
        }

        @Override
        public EditTextDialogFragmentBuilder setNegativeButtonText(String negativeButtonText, OnClickListener onClickListener) {
            commonDialogFragment.negativeButtonText = negativeButtonText;
            commonDialogFragment.mNegativeButtonListener = onClickListener;
            return this;
        }

        @Override
        public EditTextDialogFragmentBuilder setAnim(int anim) {
            commonDialogFragment.anim = anim;
            return this;
        }

        @Override
        public CommonDialogFragment bulid() {

            return commonDialogFragment;
        }

        @Override
        public CommonDialogFragment show() {
            CommonDialogFragment dialogFragment = bulid();
            dialogFragment.show(mActivity.getSupportFragmentManager(), "dialog");
            return dialogFragment;
        }
    }

    /**
     * 输入类型dialog
     */
    public static class ImageDialogFragmentBuilder extends CommonDialogBuilder {

        private final FragmentActivity mActivity;
        private final CommonDialogFragment commonDialogFragment;

        public ImageDialogFragmentBuilder(FragmentActivity activity) {
            mActivity = activity;
            commonDialogFragment = new CommonDialogFragment();
            commonDialogFragment.dialogType = DIALOG_TYPE_IMAGE;
        }

        @Override
        public ImageDialogFragmentBuilder setTitle(String title) {

            return this;
        }
        public ImageDialogFragmentBuilder setMessage(String message) {
            commonDialogFragment.message = message;
            return this;
        }

        public ImageDialogFragmentBuilder setImage(int icon){
            commonDialogFragment.icon = icon;
            return this;
        }

        @Override
        public ImageDialogFragmentBuilder setPositiveButtonText(String positiveButtonText, OnClickListener onClickListener) {
            commonDialogFragment.positiveButtonText = positiveButtonText;
            commonDialogFragment.mPositiveButtonListener = onClickListener;
            return this;
        }

        @Override
        public ImageDialogFragmentBuilder setNegativeButtonText(String negativeButtonText, OnClickListener onClickListener) {
            commonDialogFragment.negativeButtonText = negativeButtonText;
            commonDialogFragment.mNegativeButtonListener = onClickListener;
            return this;
        }

        @Override
        public ImageDialogFragmentBuilder setAnim(int anim) {
            commonDialogFragment.anim = anim;
            return this;
        }

        @Override
        public CommonDialogFragment bulid() {

            return commonDialogFragment;
        }

        @Override
        public CommonDialogFragment show() {
            CommonDialogFragment dialogFragment = bulid();
            dialogFragment.show(mActivity.getSupportFragmentManager(), "dialog");
            return dialogFragment;
        }
    }




//    //创建普通dialog
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return new AlertDialog.Builder(getActivity())
//                .setTitle("title")
//                .setPositiveButton("ok",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        }
//                )
//                .setNegativeButton("cancel",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        }
//                )
//                .create();
//    }
}
