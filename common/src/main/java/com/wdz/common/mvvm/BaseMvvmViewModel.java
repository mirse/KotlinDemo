package com.wdz.common.mvvm;

import androidx.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseMvvmViewModel<M extends BaseModel> extends ViewModel{

    protected M model;

    protected abstract void initModel();

}
