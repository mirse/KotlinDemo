package com.wdz.common.mvvm;

public interface IBaseMvvmViewModel<V> {
    V getView();
    boolean isViewAttach();
    void attachView(V view);
    void detachView();
}
