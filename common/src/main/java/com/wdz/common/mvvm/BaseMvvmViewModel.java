package com.wdz.common.mvvm;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wdz.common.net.HttpRequestStatus;

public abstract class BaseMvvmViewModel<M extends BaseModel> extends ViewModel{

    protected M model;
    protected LifecycleOwner lifecycleOwner;
    /**
     * http请求时的状态变化liveData
     */
    protected MutableLiveData<HttpRequestStatus> httpLiveData = new MutableLiveData<>();

    protected abstract void initModel(Context context);

    /**
     * 设置lifecycleOwner
     * @param lifecycleOwner
     */
    protected void setLifecycleOwner(LifecycleOwner lifecycleOwner){
        this.lifecycleOwner = lifecycleOwner;
    };

    protected LifecycleOwner getLifecycleOwner(){
        return lifecycleOwner;
    }

    public MutableLiveData<HttpRequestStatus> getHttpLiveData(){
        return httpLiveData;
    }

}
