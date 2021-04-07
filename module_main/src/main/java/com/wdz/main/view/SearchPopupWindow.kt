package com.wdz.main.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.wdz.ktcommon.view.BasePopupWindow

import com.wdz.main.R
import com.wdz.main.main.adapter.SearchAdapter

class SearchPopupWindow(context: Context) : BasePopupWindow(context) {

    lateinit var hotKeyList:MutableList<String>
    lateinit var searchAdapter:SearchAdapter
    lateinit var rvSearch:RecyclerView

    override fun initView() {
        rvSearch = getItemView(R.id.rv_search) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvSearch.layoutManager = linearLayoutManager
    }
    override fun initData() {
        hotKeyList = mutableListOf()
        searchAdapter = SearchAdapter(this.hotKeyList)
        rvSearch.adapter = searchAdapter
    }

    override val layoutId: Int
        get() = R.layout.popup_window_search
    override val popupAnimationStyle: Int
        get() = R.style.PopupWindowAnimStyle
    override val popupWidth: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT
    override val popupHeight: Int
        get() = WindowManager.LayoutParams.WRAP_CONTENT
    override val isPopupFocus: Boolean
        get() = true
    override val backgroundDrawable: ColorDrawable?
        get() = ColorDrawable(0x0000000)

    /*
    * 更新数据
    */
    fun setData(keyList:List<String>){
        this.hotKeyList.clear()
        this.hotKeyList.addAll(keyList)
        searchAdapter.notifyDataSetChanged()
    }
}