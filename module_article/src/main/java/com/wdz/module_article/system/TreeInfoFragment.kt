package com.wdz.module_article.system

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wdz.common.mvvm.kotlin.BaseKVmFragment
import com.wdz.module_article.R

import com.wdz.module_article.adapter.CategoryInfoAdapter
import com.wdz.module_article.adapter.TreeInfoAdapter
import com.wdz.module_article.bean.MainArticle
import com.wdz.module_article.databinding.ActivityTreeInfoBinding
import com.wdz.module_article.databinding.FragmentTreeInfoBinding
import kotlinx.android.synthetic.main.fragment_tree_info.*


class TreeInfoFragment(private val cid: Int, private val type: Int) : BaseKVmFragment() {
    private var treeInfoAdapter: TreeInfoAdapter ?= null
    private var categoryInfoAdapter: CategoryInfoAdapter ?= null
    private val vm by getVm<TreeInfoViewModel>()
    companion object{
        val TYPE_TREE_ARTICLE = 1
        val TYPE_VX_ARTICLE = 2
        val TYPE_PROJECT = 3
    }


    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tree_info
    }


    override fun initView() {
        (viewDataBinding as FragmentTreeInfoBinding).run {
            //绑定数据
            model = vm
            activity?.let { vm.initModel(it) }
        }

        rv_tree_info.layoutManager = LinearLayoutManager(activity)
        if (type == TYPE_PROJECT){
            categoryInfoAdapter = CategoryInfoAdapter(this@TreeInfoFragment,object: DiffUtil.ItemCallback<MainArticle>() {
                override fun areItemsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                    return oldItem.link == newItem.link
                }

                override fun areContentsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                    return oldItem == newItem
                }
            })
            rv_tree_info.adapter = categoryInfoAdapter
        }
        else{
            treeInfoAdapter = TreeInfoAdapter(object: DiffUtil.ItemCallback<MainArticle>() {
                override fun areItemsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                    return oldItem.link == newItem.link
                }

                override fun areContentsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                    return oldItem == newItem
                }
            })
            rv_tree_info.adapter = treeInfoAdapter
        }

    }

    override fun initData() {
        if (type == TYPE_TREE_ARTICLE){
            vm.getTreeInfo(cid).observe(this,
                Observer<PagedList<MainArticle>> { t -> treeInfoAdapter?.submitList(t) })
        }
        else if (type == TYPE_VX_ARTICLE){
            vm.getVxArticle(cid).observe(this,
                Observer<PagedList<MainArticle>> { t -> treeInfoAdapter?.submitList(t) })
        }
        else{
            vm.getCategoryInfo(cid).observe(this,
                Observer<PagedList<MainArticle>> { t -> categoryInfoAdapter?.submitList(t) })
        }

    }

    override fun init() {

    }

}