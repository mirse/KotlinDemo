package com.wdz.main.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.wdz.ktcommon.base.BaseKVmActivity
import com.wdz.ktcommon.constant.ARouterConstant
import com.wdz.main.R
import com.wdz.main.databinding.ActivitySearchBinding
import com.wdz.main.databinding.ActivitySearchInfoBinding

import com.wdz.main.main.adapter.SearchInfoAdapter
import com.wdz.main.main.bean.MainArticle
import kotlinx.android.synthetic.main.activity_search_info.*
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = ARouterConstant.ACTIVITY_SEARCH_INFO)
class SearchInfoActivity : BaseKVmActivity() {
    private val TAG = this::class.simpleName

    @Autowired(name = "searchName")
    lateinit var searchName: String
    private lateinit var searchInfoAdapter: SearchInfoAdapter
    private var mainArticles = listOf<MainArticle>()

    private val binding by dataBinding<ActivitySearchInfoBinding>()
    private val vm by getVm<SearchInfoViewModel>()

    override fun isTransparentBar(): Boolean {
        return true
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search_info;
    }



    override fun initView() {
        (binding as ActivitySearchInfoBinding).run {
            model = vm
            vm.initModel(this@SearchInfoActivity)

        }
        rv_search_info.layoutManager = LinearLayoutManager(this)
        searchInfoAdapter = SearchInfoAdapter(object: DiffUtil.ItemCallback<MainArticle>() {
            override fun areItemsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
                return oldItem == newItem
            }
        })
        rv_search_info.adapter = searchInfoAdapter
    }

    override fun initData() {
        Log.i(TAG, "initData: "+searchName)
        vm.getSearchList(searchName).observe(this,object: Observer<PagedList<MainArticle>>{
            override fun onChanged(t: PagedList<MainArticle>?) {
                searchInfoAdapter.submitList(t)
            }

        })
    }
}