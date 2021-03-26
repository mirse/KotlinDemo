package com.wdz.common;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

// TODO: 2021/3/24 is not accessible from java.lang.Class<androidx.startup.AppInitializer>
/**
 * startUp
 * java class TestInitializer 默认访问权限default
 * kotlin class TestInitializer 默认访问权限public
 * @Author dezhi.wang
 * @Date 2021/3/24 11:45
 */
public class MyInitializer implements Initializer<Void> {
    @NonNull
    @Override
    public Void create(@NonNull Context context) {
        if (BuildConfig.DEBUG) {
        }
        // 打印日志
        // 打印日志
        ARouter.openDebug();
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openLog();
        ARouter.init((Application) context);
        MMKV.initialize(context);
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}
