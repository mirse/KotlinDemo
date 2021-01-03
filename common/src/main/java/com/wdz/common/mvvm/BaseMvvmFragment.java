package com.wdz.common.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wdz.common.view.LoadingDialog;

import java.lang.reflect.ParameterizedType;

public abstract class BaseMvvmFragment<VM extends ViewModel,VDB extends ViewDataBinding> extends Fragment implements BaseView {

    private VDB viewDataBinding;
    private VM vm;
    public LoadingDialog mLoadingDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        vm = viewModelProvider.get(vmClass);

        return viewDataBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mLoadingDialog = new LoadingDialog(getActivity());
        initData();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 设置view.xml
     */
    public abstract int getLayoutId();

    /**
     * 初始化某些参数
     */
    public abstract void init();

    /**
     * 加载视图
     */
    public abstract void initView();

    /**
     * 加载数据
     */
    public abstract void initData();

}
