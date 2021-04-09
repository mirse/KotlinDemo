package com.wdz.ktcommon

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import java.util.*

// TODO: 2021/3/24 is not accessible from java.lang.Class<androidx.startup.AppInitializer>
/**
 * startUp
 * java class TestInitializer 默认访问权限default
 * kotlin class TestInitializer 默认访问权限public
 * @Author dezhi.wang
 * @Date 2021/3/24 11:45
 */
class MyInitializer : Initializer<Unit> {
    override fun create(context: Context): Unit {
        if (BuildConfig.DEBUG) {
        }
        // 打印日志
        // 打印日志
        ARouter.openDebug()
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openLog()
        ARouter.init(context as Application)
        MMKV.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>> {
        return ArrayList<Class<out Initializer<*>?>>()
    }
}