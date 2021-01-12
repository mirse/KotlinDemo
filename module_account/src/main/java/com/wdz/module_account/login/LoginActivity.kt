package com.wdz.module_account.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.module_account.R
import com.wdz.module_account.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

@Route(path = ARouterConstant.ACTIVITY_LOGIN)
class LoginActivity : BaseMvvmActivity<LoginViewModel,ActivityLoginBinding>(),
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
        register.setOnClickListener(this)
        bt_login.setOnClickListener(this)
        vm.loginStatus.observe(this,object :Observer<Boolean>{
            override fun onChanged(t: Boolean) {
                if (t){
                    toast("登录成功")
                    hideLoading()
                }
                else{
                    toast("登录失败")
                    hideLoading()
                }
            }

        })
    }

    override fun initData() {

    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (R.id.register == id){
            ARouter.getInstance().build(ARouterConstant.ACTIVITY_REGISTER).navigation()
            finish()
        }
        else if (R.id.bt_login == id){
            vm.login(et_id.text.toString().trim(),et_pwd.text.toString().trim())
            showLoading()
        }
    }

}