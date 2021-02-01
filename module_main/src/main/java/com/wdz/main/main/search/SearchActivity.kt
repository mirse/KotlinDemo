package com.wdz.main.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.common.util.DisplayUtils
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
        val chipsLayoutManager = ChipsLayoutManager.newBuilder(this)
            .setMaxViewsInRow(3)
            .setGravityResolver(object : IChildGravityResolver {
                override fun getItemGravity(p0: Int): Int {
                    return Gravity.CENTER
                }
            })
            .setOrientation(ChipsLayoutManager.HORIZONTAL)
            .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
            .withLastRow(true)
            .build()

        rv_search.layoutManager = chipsLayoutManager
        searchAdapter = SearchAdapter(this,hotKeyList)
        rv_search.addItemDecoration(SpacingItemDecoration(DisplayUtils.dpToPx(10),DisplayUtils.dpToPx(10)))
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