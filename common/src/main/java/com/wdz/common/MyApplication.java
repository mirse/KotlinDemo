package com.wdz.common;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;
import com.wdz.common.net.response.LoginResponse;
import com.wdz.common.util.Log;
import com.wdz.common.util.MmkvUtils;

/**
 * @Author dezhi.wang
 * @Date 2021/1/13 15:39
 */
public class MyApplication extends Application {
    private final String TAG = this.getClass().getSimpleName();
    private MyApplication instance;
    private LoginResponse loginResponse;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {

        }
        // 打印日志
        ARouter.openDebug();
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openLog();
        ARouter.init(this);
        String root = MMKV.initialize(this);
        Log.i(TAG, root);
        instance = this;
    }

    public MyApplication getInstance(){
        return instance;
    }
    public void setUserInfo(LoginResponse loginResponse){
        this.loginResponse = loginResponse;
        loginResponseMutableLiveData.postValue(loginResponse);
    }
    public MutableLiveData<LoginResponse> getUerInfo(){
        //如果loginResponse为null，重新从mmkv读取
        if (loginResponse == null){
            LoginResponse loginUser = MmkvUtils.getLoginUser();
            setUserInfo(loginUser);
        }
        return loginResponseMutableLiveData;

    }
}


