package com.wdz.module_account.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.module_account.R
import com.wdz.module_account.databinding.ActivityLoginBinding
import com.wdz.module_account.databinding.ActivityRegisterBinding
import com.wdz.module_account.login.LoginViewModel

@Route(path = ARouterConstant.ACTIVITY_REGISTER)
class RegisterActivity : BaseMvvmActivity<LoginViewModel>() {

    override fun isTransparentBar(): Boolean {
        return true;
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register;
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun isUseDataBinding(): Boolean {
        return false
    }

    override fun vmToDataBinding() {

    }
}