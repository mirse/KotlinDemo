package com.wdz.kotlindemo.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


import com.wdz.kotlindemo.FragmentAdapter
import com.wdz.kotlindemo.R
import com.wdz.kotlindemo.databinding.ActivityMainBinding
import com.wdz.ktcommon.base.BaseKVmActivity
import com.wdz.ktcommon.constant.ARouterConstant

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_buttom.*
@Route(path = ARouterConstant.ACTIVITY_MAIN)
class MainActivity : BaseKVmActivity(), View.OnClickListener {
    private var fragmentArrayList:ArrayList<Fragment> = arrayListOf();
    private val binding by dataBinding<ActivityMainBinding>()
    private val vm by getVm<MainViewModel>()

    override fun initView() {
        // TODO: 2021/3/29 可能存在不使用viewDataBinding
        (binding as ActivityMainBinding).run {
            model = vm
            vm.initModel(this@MainActivity)
        }

        fragmentArrayList.clear()
        fragmentArrayList.add(
            (ARouter.getInstance().build(ARouterConstant.FRAGMENT_MAIN)
                .navigation() as Fragment)
        )
        fragmentArrayList.add(
            (ARouter.getInstance().build(ARouterConstant.FRAGMENT_SYSTEM)
                .navigation() as Fragment)
        )
        fragmentArrayList.add(
            (ARouter.getInstance().build(ARouterConstant.FRAGMENT_ARTICLE)
                .navigation() as Fragment)
        )
        fragmentArrayList.add(
            (ARouter.getInstance().build(ARouterConstant.FRAGMENT_PROJECT)
                .navigation() as Fragment)
        )
        fragmentArrayList.add(
            (ARouter.getInstance().build(ARouterConstant.FRAGMENT_ACCOUNT)
                .navigation() as Fragment)
        )
        val fragmentAdapter =
            FragmentAdapter(this, fragmentArrayList)
        viewPager2.adapter = fragmentAdapter
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectBottomTab(position)
            }
        })
        viewPager2.currentItem = 0;


        ll_tab_1.setOnClickListener(this)
        ll_tab_2.setOnClickListener(this)
        ll_tab_3.setOnClickListener(this)
        ll_tab_4.setOnClickListener(this)
        ll_tab_5.setOnClickListener(this)
    }

    override fun initData() {

    }


    private fun selectBottomTab(position: Int) {
        ll_tab_1.setBackgroundColor(baseContext.resources.getColor(R.color.gray_holo_light))
        ll_tab_2.setBackgroundColor(baseContext.resources.getColor(R.color.gray_holo_light))
        ll_tab_3.setBackgroundColor(baseContext.resources.getColor(R.color.gray_holo_light))
        ll_tab_4.setBackgroundColor(baseContext.resources.getColor(R.color.gray_holo_light))
        ll_tab_5.setBackgroundColor(baseContext.resources.getColor(R.color.gray_holo_light))
        if (position == 0){
            ll_tab_1.setBackgroundColor(baseContext.resources.getColor(R.color.blue_color))
        }
        else if (position == 1){
            ll_tab_2.setBackgroundColor(baseContext.resources.getColor(R.color.blue_color))
        }
        else if (position == 2){
            ll_tab_3.setBackgroundColor(baseContext.resources.getColor(R.color.blue_color))
        }
        else if (position == 3){
            ll_tab_4.setBackgroundColor(baseContext.resources.getColor(R.color.blue_color))
        }
        else if (position == 4){
            ll_tab_5.setBackgroundColor(baseContext.resources.getColor(R.color.blue_color))
        }

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            val id = p0.id;
            if (R.id.ll_tab_1 == id){
                selectBottomTab(0)
                viewPager2.currentItem = 0;
            }
            else if (R.id.ll_tab_2 == id){
                selectBottomTab(1)
                viewPager2.currentItem = 1;
            }
            else if (R.id.ll_tab_3 == id){
                selectBottomTab(2)
                viewPager2.currentItem = 2;
            }
            else if (R.id.ll_tab_4 == id){
                selectBottomTab(3)
                viewPager2.currentItem = 3;
            }
            else if (R.id.ll_tab_5 == id){
                selectBottomTab(4)
                viewPager2.currentItem = 4;
            }
        }
    }

    override fun isTransparentBar(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

}
