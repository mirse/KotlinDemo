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

import com.wdz.ktcommon.base.BaseKVmFragment
import com.wdz.ktcommon.constant.ARouterConstant
import com.wdz.ktcommon.http.response.ProjectResponse

import com.wdz.module_article.R

import com.wdz.module_article.adapter.ViewPager2Adapter
import com.wdz.module_article.databinding.ActivityTreeInfoBinding
import com.wdz.module_article.system.TreeInfoFragment
import com.wdz.module_article.system.TreeInfoFragment.Companion.TYPE_PROJECT
import com.wdz.module_article.system.TreeInfoViewModel
import kotlinx.android.synthetic.main.activity_tree_info.*


@Route(path = ARouterConstant.FRAGMENT_PROJECT)
class ProjectFragment : BaseKVmFragment() {
    private val vm by getVm<TreeInfoViewModel>()
    val list = mutableListOf<Int>()
    private lateinit var mAdapter:ViewPager2Adapter
//    private val mAdapter by lazy {
//        ViewPager2Adapter(this.childFragmentManager,lifecycle,
//            TYPE_PROJECT, list)
//    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tree_info
    }

    override fun init() {

    }

    override fun initView() {
        (viewDataBinding as ActivityTreeInfoBinding).run {
            //绑定数据
            model = vm
            activity?.let { vm.initModel(it) }
        }
        mAdapter = ViewPager2Adapter(this.childFragmentManager,lifecycle,
            TYPE_PROJECT, list)
        viewPager.adapter = mAdapter
    }

    override fun initData() {
        vm.getCategory().observe(this,
            Observer<List<ProjectResponse>> { t ->
                if (t!=null){
                    val projectList = t as MutableList<ProjectResponse>
                    list.clear()
                    for (i in t.indices){
                        list.add(t[i].id)
                    }
                    mAdapter.notifyDataSetChanged()
                    TabLayoutMediator(tableLayout,viewPager,true,
                        TabLayoutMediator.TabConfigurationStrategy { tab, position -> tab.text = projectList[position].name }).attach()
                }
            })
    }


    override fun isUseDataBinding(): Boolean {
        return true
    }


}