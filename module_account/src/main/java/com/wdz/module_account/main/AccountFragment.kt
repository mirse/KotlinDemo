package com.wdz.module_account.main

import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


import com.wdz.common.constant.ARouterConstant




import com.wdz.common.util.Log
import com.wdz.common.view.dialog.CommonDialogFragment
import com.wdz.ktcommon.MyApplication
import com.wdz.ktcommon.base.BaseKVmFragment
import com.wdz.ktcommon.http.HttpRequestStatus
import com.wdz.ktcommon.response.LoginResponse
import com.wdz.module_account.R
import com.wdz.module_account.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.support.v4.toast

@Route(path = ARouterConstant.FRAGMENT_ACCOUNT)
public class AccountFragment : BaseKVmFragment(),
    View.OnClickListener {
    private val TAG = this::class.simpleName
    private var userInfo: LoginResponse? = null
    private var dialog:CommonDialogFragment? = null

    private val vm by getVm<AccountViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_account
    }

    override fun init() {

    }

    override fun initView() {
        (viewDataBinding as FragmentAccountBinding).run {
            //绑定数据
            model = vm
            activity?.let { vm.initModel(it) }
        }
        cl_user_info.setOnClickListener(this)
    }

    override fun initData() {

        //userInfo状态监听
        (activity?.application as MyApplication).getUerInfo().observe(this,
            Observer<LoginResponse> { t ->
                Log.i(TAG, "t:$t")
                userInfo = t
                val name = if (userInfo != null) userInfo?.nickname else "user name"
                tv_name.text = name
            })

        //退出账号状态监听
        vm.httpLiveData.observe(this, Observer {
            when(it){
                HttpRequestStatus.REQUESTING -> {
                    showLoading()
                }
                HttpRequestStatus.REQUEST_SUCCESS -> {
                    toast("退出账号成功")
                    (activity?.application as MyApplication).setUserInfo(null)
                    hideLoading()
                    dialog?.dismiss()
                }
                HttpRequestStatus.REQUEST_FAIL -> {
                    toast("退出账号失败:"+it.msg)
                    hideLoading()
                    dialog?.dismiss()
                }
                else -> {

                }
            }
        })

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
        dialog = CommonDialogFragment.MessageDialogFragmentBuilder(activity)
            .setTitle("提示")
            .setMessage("退出账号？")
            .setNegativeButtonText("取消") {

            }
            .setPositiveButtonText("确定") {
                vm.logoutUser()
            }
            .show()
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }


}