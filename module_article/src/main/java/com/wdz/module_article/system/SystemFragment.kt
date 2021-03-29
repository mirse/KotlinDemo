package com.wdz.module_article.system

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wdz.common.base.adapter.BaseRecyclerViewAdapter
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.databind.BaseBindRecyclerViewAdapter

import com.wdz.common.mvvm.kotlin.BaseKVmFragment
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.R
import com.wdz.module_article.adapter.SystemAdapter
import com.wdz.module_article.databinding.FragmentSystemBinding
import kotlinx.android.synthetic.main.fragment_system.*


@Route(path = ARouterConstant.FRAGMENT_SYSTEM)
class SystemFragment : BaseKVmFragment() {
    private val TAG = this::class.simpleName
    private val treeList = mutableListOf<TreeResponse>()
    private val mAdapter by lazy {
        SystemAdapter(treeList)
    }
    private val vm by getVm<SystemViewModel>()


    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun init() {

    }

    override fun initView() {
        (viewDataBinding as FragmentSystemBinding).run {
            //绑定数据
            model = vm
            activity?.let { vm.initModel(it) }
        }
        rv_system.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        mAdapter.setOnClickListener(BaseBindRecyclerViewAdapter.OnItemClickListener { `object`, position ->
            if (`object` is TreeResponse) {
                //跳转至搜索详情界面
                ARouter.getInstance().build(ARouterConstant.ACTIVITY_TREE_INFO)
                    .withParcelable("systemFragment", `object`)
                    .navigation()
            }
        })
    }

    override fun initData() {
        vm.tree.observe(this,object: Observer<List<TreeResponse>> {
            override fun onChanged(t: List<TreeResponse>?) {
                Log.i(TAG, "onChanged: "+t)
                if (t != null) {
                    treeList.clear()
                    treeList.addAll(t.toMutableList())
                    mAdapter.notifyDataSetChanged()
                }


            }
        })

        vm.getSystem()

    }


    override fun isUseDataBinding(): Boolean {
        return true
    }

}