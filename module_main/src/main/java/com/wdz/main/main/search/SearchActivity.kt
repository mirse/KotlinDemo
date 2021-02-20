package com.wdz.main.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import com.beloo.widget.chipslayoutmanager.gravity.IChildGravityResolver
import com.wdz.common.base.adapter.BaseRecyclerViewAdapter
import com.wdz.common.constant.ARouterConstant
import com.wdz.common.mvvm.BaseMvvmActivity
import com.wdz.common.room.entity.History
import com.wdz.common.util.DisplayUtils
import com.wdz.common.view.CommonTitleBar
import com.wdz.main.R
import com.wdz.main.databinding.ActivitySearchBinding
import com.wdz.main.databinding.FragmentHomeBinding
import com.wdz.main.main.adapter.SearchAdapter
import com.wdz.main.main.adapter.SearchHistoryAdapter
import com.wdz.main.main.bean.MainArticle
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

@Route(path = ARouterConstant.ACTIVITY_SEARCH)
class SearchActivity : BaseMvvmActivity<SearchViewModel>() {
    var hotKeyList = mutableListOf<String>()
    var searchHistoryList = mutableListOf<History>()
    lateinit var searchAdapter: SearchAdapter
    lateinit var searchHistoryAdapter: SearchHistoryAdapter
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
        val searchHistorychipsLayoutManager = ChipsLayoutManager.newBuilder(this)
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
        searchAdapter = SearchAdapter(this, hotKeyList)
        rv_search.addItemDecoration(
            SpacingItemDecoration(
                DisplayUtils.dpToPx(10),
                DisplayUtils.dpToPx(10)
            )
        )
        rv_search.adapter = searchAdapter

        rv_history.layoutManager = searchHistorychipsLayoutManager
        searchHistoryAdapter = SearchHistoryAdapter(this, searchHistoryList)
        rv_history.addItemDecoration(
            SpacingItemDecoration(
                DisplayUtils.dpToPx(10),
                DisplayUtils.dpToPx(10)
            )
        )
        rv_history.adapter = searchHistoryAdapter

        /*
        * 搜索历史的点击事件
        */
        searchHistoryAdapter.setOnClickListener(object :
            BaseRecyclerViewAdapter.OnItemClickListener {
            override fun onClickNormal(`object`: Any?, position: Int) {
                if (`object` is History) {
                    val history = `object`
                    //跳转至搜索详情界面
                    ARouter.getInstance().build(ARouterConstant.ACTIVITY_SEARCH_INFO)
                        .withString("searchName", history.searchTitle)
                        .navigation()
                }
            }
        })

        /*
        * 搜索框的点击事件
        */
        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH ||
                    (p2 != null && p2.getKeyCode() == KeyEvent.KEYCODE_SEARCH)
                ) {
                    var isSearchHistorySave = false
                    val searchTitle = p0?.text.toString().trim()
                    //输入栏字符串长度不为0
                    if (searchTitle.isNotEmpty()) {
                        for (i in searchHistoryList.indices) {
                            // TODO: 2021/2/20  kotlin ==与equal作用相同 ，a == b ->a?.equals(b)?:b==null
                            //  ，比较两个对象的引用是否相等使用 === 操作符
                            if (searchHistoryList[i].searchTitle == searchTitle) {
                                isSearchHistorySave = true
                                break
                            }
                        }
                        if (!isSearchHistorySave) {
                            //存储搜索历史
                            val history = History()
                            history.searchTitle = searchTitle
                            vm.saveSearchHistory(history)
                        }
                        //跳转至搜索详情界面
                        ARouter.getInstance().build(ARouterConstant.ACTIVITY_SEARCH_INFO)
                            .withString("searchName", p0?.text.toString().trim())
                            .navigation()
                    }

                }

                return false
            }
        })

        img_back.setOnClickListener({
            finish()
        })

    }

    override fun initData() {
        vm.getHotKey()
        vm.hotKeyList.observe(this, object : Observer<List<String>> {
            override fun onChanged(t: List<String>?) {
                if (t != null) {
                    hotKeyList.clear()
                    hotKeyList.addAll(t)
                    searchAdapter.notifyDataSetChanged()
                }

            }
        })

        vm.getSearchHistory()
        vm.searchHistoryList.observe(this, object : Observer<MutableList<History>> {
            override fun onChanged(t: MutableList<History>?) {
                if (t != null) {
                    if (t.isEmpty()) {
                        tv_history.visibility = View.GONE
                    } else {
                        tv_history.visibility = View.VISIBLE
                        searchHistoryList.clear()
                        searchHistoryList.addAll(t)
                        searchHistoryAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}