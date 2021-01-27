package com.wdz.main.main.adapter

import android.content.Context
import android.widget.TextView
import com.wdz.common.base.adapter.SingleTypeAdapter
import com.wdz.main.R

public class SearchAdapter(context:Context,list:List<String>):SingleTypeAdapter<String>(context,list) {
    override fun getEmptyLayoutId(): Int {
        return 0
    }


    override fun getLayoutId(): Int {
        return R.layout.recycler_item_main_search
    }

    override fun getHeadLayoutId(): Int {
        return 0
    }


    override fun bindData(holder: BaseViewHolder?, data: String?, position: Int) {
        val title = holder?.getView(R.id.tv_title) as TextView
        title.setText(data)
    }
}