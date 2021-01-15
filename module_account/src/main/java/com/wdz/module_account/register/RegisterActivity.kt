package com.wdz.module_account.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.jakewharton.rxbinding3.widget.textChanges
import com.wdz.common.MyApplication
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.common.net.HttpRequestStatus
import com.wdz.module_account.R
import com.wdz.module_account.databinding.ActivityLoginBinding
import com.wdz.module_account.databinding.ActivityRegisterBinding
import com.wdz.module_account.login.LoginViewModel
import com.wdz.module_account.login.bean.RegisterStatus
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_id
import kotlinx.android.synthetic.main.activity_register.et_pwd
import org.jetbrains.anko.toast

@Route(path = ARouterConstant.ACTIVITY_REGISTER)
class RegisterActivity : BaseMvvmActivity<RegisterViewModel>(), View.OnClickListener {

    override fun isTransparentBar(): Boolean {
        return true;
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register;
    }

    override fun initView() {
        et_id.setHint("输入账号")
        et_pwd.setHint("输入密码")
        et_pwd_again.setHint("再次输入密码")
        val disposable = Observable.combineLatest(et_id.textChanges(),et_pwd.textChanges(),et_pwd_again.textChanges(),
            object : Function3<CharSequence, CharSequence,CharSequence,Boolean> {
                override fun apply(t1: CharSequence, t2: CharSequence, t3: CharSequence): Boolean {
                    return t1.isNotBlank() && t2.isNotBlank() && t3.isNotBlank()
                }

            }
        ).subscribe(object: Consumer<Boolean> {
            override fun accept(t: Boolean?) {
                bt_register.isEnabled = t!!
            }
        })
        addDisposable(disposable)
    }

    override fun initData() {
        vm.registerStatus.observe(this,object: Observer<RegisterStatus> {
            override fun onChanged(t: RegisterStatus?) {
                if (t != null) {
                    //注册完成后登录状态
                    if (t.isLogin){
                        if (t.requestStatus == HttpRequestStatus.REQUEST_SUCCESS){
                            (application as MyApplication).setUserInfo(t.loginResponse)
                            toast("登录成功")
                            hideLoading()
                        } else if (t.requestStatus == HttpRequestStatus.REQUEST_FAIL){
                            toast("登录失败:"+t.loginErrorMsg)
                            hideLoading()
                        }
                    }
                    //注册状态
                    else{
                        if (t.requestStatus == HttpRequestStatus.REQUEST_SUCCESS){
                            toast("注册成功")
                            hideLoading()
                        } else if (t.requestStatus == HttpRequestStatus.REQUEST_FAIL){
                            toast("注册失败:"+t.errorMsg)
                            hideLoading()
                        }
                        else if (t.requestStatus == HttpRequestStatus.REQUESTING){
                            showLoading()
                        }
                    }

                }
            }

        })
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun vmToDataBinding() {
        (viewDataBinding as ActivityRegisterBinding).model = vm
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id!=null){

        }
    }
}