package com.wdz.ktcommon.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.wdz.ktcommon.R

/**

 * @Author dezhi.wang

 * @Date 2021/3/31 10:39

 */
class LoadingDialog(context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //去除自定义对话框的标题栏，Android 4.4以下会出现标题栏
        //去除自定义对话框的标题栏，Android 4.4以下会出现标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loading_dialog)
        val mLoading =
            findViewById<ImageView>(R.id.loading)
        val window = this.window
        if (window != null) {
            window.setGravity(Gravity.CENTER)
            //        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
//        window.setWindowAnimations(R.style.TimePickDialogAnumation);
            //必须设置否则对话框会变小
            val wlp = window.attributes
            wlp.width = WindowManager.LayoutParams.WRAP_CONTENT
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = wlp
        }

        setCancelable(false)
    }
}