package com.wdz.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * @author wdz
 */
public abstract class BaseFragment extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutId(), container, false);
        ButterKnife.bind(this, view);
        init();
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /**
     * 设置布局view
     * @return
     */
    protected abstract int setLayoutId();
    /**
     * 初始化成员变量，intent传输的数据
     */
    protected abstract void init();
    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();



    /**
     * 获取上下文
     * @return
     */
    public Context getMContext(){
        return getContext();
    }

    public View getContentView(){
        return view;
    }

}
