package com.wdz.common.mvvm;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.wdz.common.view.LoadingDialog;

import java.lang.reflect.ParameterizedType;

public abstract class BaseMvvmActivity<VM extends BaseMvvmViewModel> extends AppCompatActivity implements BaseView {

    protected VM vm;
    private int mColor = 0;
    public LoadingDialog mLoadingDialog;
    protected ViewDataBinding viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransparentBar();
        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        vm = new ViewModelProvider(this).get(vmClass);
        vm.initModel();
        if (isUseDataBinding()){
            initDataBinding();
        }
        else{
            setContentView(getLayoutId());
        }
        initView();
        mLoadingDialog = new LoadingDialog(this);
        initData();


    }

    private void initDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewDataBinding.setLifecycleOwner(this);
        vmToDataBinding();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setTransparentBar() {
        if (isTransparentBar()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (mColor == 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(Color.TRANSPARENT);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(mColor);
                }
            }

            //适配凹凸异形屏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                getWindow().setAttributes(lp);
            }

        }
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
     * 状态栏是否透明
     * @return
     */
    public abstract boolean isTransparentBar();
    /**
     * 是否使用databinding
     * @return
     */
    public abstract boolean isUseDataBinding();
    /**
     * 设置ui视图
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 只有isUseDataBinding == true时需要设置
     * 将viewModel与DataBinding关联
     */
    public abstract void vmToDataBinding();
    /**
     * 初始化view相关
     */
    public abstract void initView();
    /**
     * 初始化data相关
     */
    public abstract void initData();
}
