package com.wdz.kotlindemo.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.util.MmkvUtils
import com.wdz.common.MyApplication
import com.wdz.kotlindemo.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val loginUser = MmkvUtils.getLoginUser()
        if (loginUser!=null){
            (application as MyApplication).setUserInfo(loginUser)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            ARouter.getInstance().build(ARouterConstant.ACTIVITY_MAIN).navigation()
            finish()
        }, 300)

    }
}
