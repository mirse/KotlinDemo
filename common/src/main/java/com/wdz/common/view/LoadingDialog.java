package com.wdz.common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.wdz.common.R;


/**
 * loading
 * @author wdz
 */
public class LoadingDialog extends Dialog {
    private Context mContext;
    private Animation mRotate;
    private ImageView mLoading;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除自定义对话框的标题栏，Android 4.4以下会出现标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_dialog);
        mLoading = findViewById(R.id.loading);
        Window window = this.getWindow();
        if (window!=null)
        {
            window.setGravity(Gravity.CENTER);
//        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
//        window.setWindowAnimations(R.style.TimePickDialogAnumation);
            //必须设置否则对话框会变小
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(wlp);
        }

        this.setCancelable(false);
    }

    @Override
    public void show() {
        super.show();

////        if (mLoading!=null&&mRotate!=null)
////        {
////            mLoading.startAnimation(mRotate);
////        }
//        Window window = this.getWindow();
////        window.setGravity(Gravity.BOTTOM);
////        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
////        window.setWindowAnimations(R.style.TimePickDialogAnumation);
//        //必须设置否则对话框会变小
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(wlp);

    }

}
