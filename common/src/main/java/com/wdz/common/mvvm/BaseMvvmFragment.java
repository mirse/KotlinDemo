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

public abstract class BaseMvvmFragment<VM extends BaseMvvmViewModel> extends Fragment implements BaseView {


    protected VM vm;
    public LoadingDialog mLoadingDialog;
    protected ViewDataBinding viewDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        vm = new ViewModelProvider(this).get(vmClass);
        vm.initModel();
        if (isUseDataBinding()){
            viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            viewDataBinding.setLifecycleOwner(this);
            return viewDataBinding.getRoot();
        }
        else{
            return inflater.inflate(getLayoutId(), container, false);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewDataBinding!=null){
            viewDataBinding = null;
        }
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

    /**
     * 只有isUseDataBinding == true时需要设置
     * 将viewModel与DataBinding关联
     */
    public abstract void vmToDataBinding();
    /**
     * 是否使用databinding
     * @return
     */
    public abstract boolean isUseDataBinding();

}
