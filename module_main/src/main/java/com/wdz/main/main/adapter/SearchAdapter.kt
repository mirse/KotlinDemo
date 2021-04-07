package com.wdz.main.main.adapter

import android.content.Context
import android.widget.TextView
import androidx.databinding.ViewDataBinding

import com.wdz.ktcommon.adapter.SingleBindTypeAdapter
import com.wdz.main.R

import com.wdz.main.databinding.RecyclerItemMainSearchBinding

public class SearchAdapter(list:List<String>):SingleBindTypeAdapter<String>(list) {

    override val layoutId: Int
        get() = R.layout.recycler_item_main_search
    override val headLayoutId: Int
        get() = 0
    override val emptyLayoutId: Int
        get() = 0

    override fun bindData(
        binding: ViewDataBinding,
        holder: BaseViewHolder,
        data: Any,
        position: Int
    ) {
        val databinding = binding as? RecyclerItemMainSearchBinding
        databinding?.run {
            databinding.data = data as String?
        }
    }


}