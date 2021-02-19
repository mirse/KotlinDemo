package com.wdz.main.main.adapter

import android.content.Context
import android.widget.TextView
import com.wdz.common.base.adapter.SingleTypeAdapter
import com.wdz.common.room.entity.History
import com.wdz.main.R

public class SearchHistoryAdapter(context:Context, list:List<History>):SingleTypeAdapter<History>(context,list) {
    override fun getEmptyLayoutId(): Int {
        return 0
    }


    override fun getLayoutId(): Int {
        return R.layout.recycler_item_main_search
    }

    override fun getHeadLayoutId(): Int {
        return 0
    }


    override fun bindData(holder: BaseViewHolder?, data: History?, position: Int) {
        val title = holder?.getView(R.id.tv_title) as TextView
        if (data != null) {
            title.text = data.searchTitle
        }
    }
}