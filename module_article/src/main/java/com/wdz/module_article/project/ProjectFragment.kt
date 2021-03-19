package com.wdz.module_article.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmFragment
import com.wdz.common.net.response.ProjectResponse
import com.wdz.common.net.response.WxResponse
import com.wdz.module_article.R

import com.wdz.module_article.adapter.ViewPager2Adapter
import com.wdz.module_article.databinding.ActivityTreeInfoBinding
import com.wdz.module_article.system.TreeInfoFragment.Companion.TYPE_PROJECT
import com.wdz.module_article.system.TreeInfoViewModel
import kotlinx.android.synthetic.main.activity_tree_info.*


@Route(path = ARouterConstant.FRAGMENT_PROJECT)
class ProjectFragment : BaseMvvmFragment<TreeInfoViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_tree_info
    }

    override fun init() {

    }

    override fun initView() {

    }

    override fun initData() {
        vm.getCategory().observe(this,object : Observer<List<ProjectResponse>> {
            override fun onChanged(t: List<ProjectResponse>?) {
                if (t!=null){
                    val projectList = t as MutableList<ProjectResponse>
                    val list = mutableListOf<Int>()
                    for (i in t.indices){
                        list.add(t[i].id)
                    }

                    val mAdapter = ViewPager2Adapter(this@ProjectFragment.childFragmentManager,lifecycle,TYPE_PROJECT,list)
                    viewPager.adapter = mAdapter

                    TabLayoutMediator(tableLayout,viewPager,true, object:
                        TabLayoutMediator.TabConfigurationStrategy{
                        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                            tab.text = projectList[position].name
                        }

                    }).attach()
                }
            }
        })
    }

    override fun vmToDataBinding() {
        (viewDataBinding as ActivityTreeInfoBinding).model = vm
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }


}