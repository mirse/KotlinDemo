package com.wdz.module_account.main

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.MyApplication
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmFragment
import com.wdz.common.net.HttpRequestStatus
import com.wdz.common.net.base.BaseResponse
import com.wdz.common.net.bean.NetRequestStatus
import com.wdz.common.net.response.LoginResponse
import com.wdz.common.util.Log
import com.wdz.common.view.dialog.CommonDialogFragment
import com.wdz.module_account.R
import com.wdz.module_account.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*

@Route(path = ARouterConstant.FRAGMENT_ACCOUNT)
public class AccountFragment : BaseMvvmFragment<AccountViewModel>(),
    View.OnClickListener {
    private val TAG = this::class.simpleName
    private var userInfo: LoginResponse? = null
    private var dialog:CommonDialogFragment? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_account
    }

    override fun init() {

    }

    override fun initView() {
        cl_user_info.setOnClickListener(this)
    }

    override fun initData() {

        //userInfo状态监听
        (activity?.application as MyApplication).uerInfo.observe(this,object:Observer<LoginResponse>{
            override fun onChanged(t: LoginResponse?) {
                Log.i(TAG,"t:"+t.toString())
                userInfo = t
                val name = if (userInfo != null) userInfo?.nickname else "user name"
                tv_name.text = name
            }

        })
        //退出账号状态监听
        vm.logoutStatus.observe(this,object: Observer<NetRequestStatus<BaseResponse.DataBean>>{
            override fun onChanged(t: NetRequestStatus<BaseResponse.DataBean>?) {
                if (t!=null){
                    if (t.requestStatus == HttpRequestStatus.REQUEST_SUCCESS){
                        toast("退出账号成功")
                        (activity?.application as MyApplication).setUserInfo(null)
                        hideLoading()
                        dialog?.dismiss()
                    } else if (t.requestStatus == HttpRequestStatus.REQUEST_FAIL){
                        toast("退出账号失败:"+t.errorMsg)
                        hideLoading()
                        dialog?.dismiss()
                    }
                    else if (t.requestStatus == HttpRequestStatus.REQUESTING){
                        showLoading()
                    }
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

    override fun vmToDataBinding() {
        (viewDataBinding as FragmentAccountBinding).model = vm
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }


}