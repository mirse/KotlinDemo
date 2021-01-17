package com.wdz.module_account.main

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.MyApplication
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmFragment
import com.wdz.common.net.response.LoginResponse
import com.wdz.common.view.dialog.CommonDialogFragment
import com.wdz.module_account.R
import kotlinx.android.synthetic.main.fragment_account.*

@Route(path = ARouterConstant.FRAGMENT_ACCOUNT)
public class AccountFragment : BaseMvvmFragment<AccountViewModel>(),
    View.OnClickListener {
    private lateinit var userInfo:LoginResponse
    override fun getLayoutId(): Int {
        return R.layout.fragment_account
    }

    override fun init() {

    }

    override fun initView() {
        cl_user_info.setOnClickListener(this)
    }

    override fun initData() {
        userInfo = (activity?.application as MyApplication).uerInfo
        tv_name.text = userInfo.nickname


    }




    override fun onClick(p0: View?) {
        val id = p0?.id;
        if (R.id.cl_user_info == id){
            //如果已登录显示用户信息修改，未登录显示登录界面
            if (userInfo == null){
                ARouter.getInstance().build(ARouterConstant.ACTIVITY_LOGIN).navigation();
            }
            else{
                showLogoutDialog()
            }

        }
    }

    private fun showLogoutDialog() {
        CommonDialogFragment.MessageDialogFragmentBuilder(activity)
            .setTitle("提示")
            .setMessage("退出账号？")
            .setNegativeButtonText("取消") {

            }
            .setPositiveButtonText("确定") {

            }
    }

    override fun vmToDataBinding() {

    }

    override fun isUseDataBinding(): Boolean {
        return false
    }


}