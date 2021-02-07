package com.wdz.common.mvvm;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseMvvmViewModel<M extends BaseModel> extends ViewModel{

    protected M model;
    protected LifecycleOwner lifecycleOwner;

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

}
