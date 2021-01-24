package com.wdz.main.main.adapter

import android.content.Context
import com.wdz.common.base.BaseRecyclerViewAdapter

public class SearchAdapter(context:Context):BaseRecyclerViewAdapter<String>(context) {
    override fun getEmptyLayoutId(): Int {
        return 0
    }

    override fun getHeaderLayoutId(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun bindData(holder: BaseViewHolder?, data: String?, position: Int) {

    }

    override fun bindHeaderData(holder: BaseViewHolder?) {
       
    }

    override fun isHeaderOnlyLine(): Boolean {
        return true
    }
}