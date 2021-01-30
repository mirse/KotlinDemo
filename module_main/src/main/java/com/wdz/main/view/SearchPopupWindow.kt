package com.wdz.main.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdz.common.net.BaseObserver
import com.wdz.common.net.NetManager
import com.wdz.common.net.response.HotKeyResponse
import com.wdz.common.view.BasePopupWindow
import com.wdz.main.R
import com.wdz.main.main.adapter.SearchAdapter

class SearchPopupWindow(context: Context?) : BasePopupWindow(context) {

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
        searchAdapter = SearchAdapter(context,this.hotKeyList)
        rvSearch.adapter = searchAdapter
    }
    /*
    * 更新数据
    */
    fun setData(keyList:List<String>){
        this.hotKeyList.clear()
        this.hotKeyList.addAll(keyList)
        searchAdapter.notifyDataSetChanged()
    }


    override fun getLayoutId(): Int {
        return R.layout.popup_window_search
    }



    override fun getPopupWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getPopupHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    override fun getBackgroundDrawable(): ColorDrawable {
        return ColorDrawable(0x0000000)
    }

    override fun isPopupFocus(): Boolean {
        return true
    }

    override fun getPopupAnimationStyle(): Int {
        return R.style.PopupWindowAnimStyle
    }
}