package com.wdz.module_account.login

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding3.widget.textChanges

import com.wdz.common.MyApplication
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.common.net.HttpRequestStatus
import com.wdz.module_account.R
import com.wdz.module_account.databinding.ActivityLoginBinding
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


@Route(path = ARouterConstant.ACTIVITY_LOGIN)
class LoginActivity : BaseMvvmActivity<LoginViewModel>(),
    View.OnClickListener {
    private val TAG = "LoginActivity"

    override fun isTransparentBar(): Boolean {
        return true;
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login;
    }

    override fun initView() {

        et_id.setHint("请输入账号")
        et_pwd.setHint("请输入密码")
        register.setOnClickListener(this)
        bt_login.setOnClickListener(this)
        iv_return.setOnClickListener(this)

        val disposable = Observable.combineLatest(et_id.textChanges(),et_pwd.textChanges(),
            object : BiFunction<CharSequence, CharSequence, Boolean> {
                override fun apply(p0: CharSequence, p1: CharSequence): Boolean {
                    return p0.isNotBlank() && p1.isNotBlank()
                }
            }
        ).subscribe(object:Consumer<Boolean>{
            override fun accept(t: Boolean?) {
                bt_login.isEnabled = t!!
            }
        })
        addDisposable(disposable)
    }

    override fun initData() {
        vm.loginStatus.observe(this,
            { t ->
                if (t != null) {
                    if (t.requestStatus == HttpRequestStatus.REQUEST_SUCCESS){
                        (application as MyApplication).setUserInfo(t.loginResponse)
                        toast("登录成功")
                        hideLoading()
                        finish()
                    } else if (t.requestStatus == HttpRequestStatus.REQUEST_FAIL){
                        toast("登录失败:"+t.errorMsg)
                        hideLoading()
                    }
                    else if (t.requestStatus == HttpRequestStatus.REQUESTING){
                        showLoading()
                    }
                }
            })
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (R.id.register == id){
            ARouter.getInstance().build(ARouterConstant.ACTIVITY_REGISTER).navigation()
            finish()
        }

        else if (R.id.iv_return == id){
            finish()
        }
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun vmToDataBinding() {
        (viewDataBinding as ActivityLoginBinding).model = vm
    }

}