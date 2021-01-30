package com.wdz.main.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.main.R
import com.wdz.main.databinding.ActivitySearchBinding
import com.wdz.main.databinding.FragmentHomeBinding
import com.wdz.main.main.adapter.SearchAdapter
import com.wdz.main.main.bean.MainArticle
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

@Route(path = ARouterConstant.ACTIVITY_SEARCH)
class SearchActivity : BaseMvvmActivity<SearchViewModel>() {
    var hotKeyList = mutableListOf<String>()
    lateinit var searchAdapter:SearchAdapter
    override fun isTransparentBar(): Boolean {
        return true
    }

    override fun isUseDataBinding(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun vmToDataBinding() {
        (viewDataBinding as ActivitySearchBinding).model = vm
    }

    override fun initView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_search.layoutManager = linearLayoutManager
        searchAdapter = SearchAdapter(this,hotKeyList)
        rv_search.adapter = searchAdapter
    }

    override fun initData() {
        vm.getHotKey()
        vm.hotKeyList.observe(this,object: Observer<List<String>> {
            override fun onChanged(t: List<String>?) {
                if (t != null) {
                    hotKeyList.clear()
                    hotKeyList.addAll(t)
                    searchAdapter.notifyDataSetChanged()
                }

            }

        })
    }
}