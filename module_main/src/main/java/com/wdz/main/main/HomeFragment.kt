package com.wdz.main.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmFragment
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.R
import com.wdz.main.databinding.FragmentHomeBinding
import com.wdz.main.main.adapter.HomeAdapter
import com.wdz.main.main.bean.MainArticle
import kotlinx.android.synthetic.main.fragment_home.*


@Route(path = ARouterConstant.FRAGMENT_MAIN)
class HomeFragment : BaseMvvmFragment<HomeViewModel>() {

    var mainArticles = listOf<MainArticle>()
    private lateinit var homeAdapter: HomeAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun init() {

    }

    override fun initView() {
        rv_article_main.layoutManager = LinearLayoutManager(activity)
        homeAdapter =   HomeAdapter(mainArticles,object: DiffUtil.ItemCallback<MainArticle>() {
            override fun areItemsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                return oldItem == newItem
            }
        })
        rv_article_main.adapter = homeAdapter
    }

    override fun initData() {
        vm.getArticle()

        vm.articleList.observe(this,object:Observer<PagedList<MainArticle>>{
            override fun onChanged(t: PagedList<MainArticle>?) {
                homeAdapter.submitList(t)
            }
        })
    }

    override fun vmToDataBinding() {
        (viewDataBinding as FragmentHomeBinding).model = vm
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

}