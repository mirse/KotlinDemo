package com.wdz.kotlindemo.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter

import com.wdz.kotlindemo.R
import com.wdz.ktcommon.MyApplication
import com.wdz.ktcommon.constant.ARouterConstant
import com.wdz.ktcommon.utils.getLoginUser

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val loginUser = getLoginUser()
        if (loginUser!=null){
            (application as MyApplication).setUserInfo(loginUser)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            ARouter.getInstance().build(ARouterConstant.ACTIVITY_MAIN).navigation()
            finish()
        }, 300)

    }
}
