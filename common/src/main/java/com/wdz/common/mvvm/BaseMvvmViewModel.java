package com.wdz.common.mvvm;

import androidx.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseMvvmViewModel<V,M extends BaseModel> extends ViewModel implements IBaseMvvmViewModel<V>{

    protected M model;

    /**
     * view接口的弱引用
     */
    protected Reference<V> mViewRef;


    /**
     * @return 获取view
     */
    @Override
    public V getView(){
        if (mViewRef != null){
            return mViewRef.get();
        }
        else{
            return null;
        }

    }

    /**
     * @return 是否与view建立了关联
     */
    @Override
    public boolean isViewAttach(){
        return null != mViewRef && null != mViewRef.get() ;
    }
    /**
     * 与view建立关联
     * @param view
     */
    @Override
    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    /**
     * 与view取消关联
     */
    @Override
    public void detachView()
    {
        if (null != mViewRef)
        {
            mViewRef.clear();
            mViewRef = null;
        }

    }



    protected abstract void initModel();

}
