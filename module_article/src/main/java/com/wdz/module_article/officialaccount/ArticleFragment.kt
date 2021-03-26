package com.wdz.module_article.officialaccount

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
import com.wdz.common.net.response.WxResponse
import com.wdz.module_article.R
import com.wdz.module_article.adapter.ViewPager2Adapter


import com.wdz.module_article.databinding.ActivityTreeInfoBinding
import com.wdz.module_article.system.SystemViewModel
import com.wdz.module_article.system.TreeInfoFragment
import com.wdz.module_article.system.TreeInfoFragment.Companion.TYPE_VX_ARTICLE
import com.wdz.module_article.system.TreeInfoViewModel
import kotlinx.android.synthetic.main.activity_tree_info.*


@Route(path = ARouterConstant.FRAGMENT_ARTICLE)
class ArticleFragment : BaseMvvmFragment<TreeInfoViewModel>() {

    var wxAuthorList = mutableListOf<WxResponse>()
    val list = mutableListOf<Int>()
    private lateinit var mAdapter:ViewPager2Adapter
    override fun getLayoutId(): Int {
        return R.layout.activity_tree_info
    }

    override fun init() {

    }

    override fun initView() {
        mAdapter = ViewPager2Adapter(this@ArticleFragment.childFragmentManager,lifecycle,
            TYPE_VX_ARTICLE, list)
        viewPager.adapter = mAdapter
    }

    override fun initData() {

        vm.getWx().observe(this,object : Observer<List<WxResponse>>{
            override fun onChanged(t: List<WxResponse>?) {
                if (t!=null){
                    wxAuthorList = t as MutableList<WxResponse>
                    list.clear()
                    for (i in t.indices){
                        list.add(t[i].id)
                    }
                    mAdapter.notifyDataSetChanged()



                    TabLayoutMediator(tableLayout,viewPager,true, object:
                        TabLayoutMediator.TabConfigurationStrategy{
                        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                            tab.text = wxAuthorList[position].name
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