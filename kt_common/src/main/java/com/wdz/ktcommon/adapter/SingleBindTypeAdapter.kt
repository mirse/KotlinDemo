package com.wdz.ktcommon.adapter

import androidx.databinding.ViewDataBinding
import java.util.*

abstract class SingleBindTypeAdapter<T>(list: List<T>) :
    BaseBindRecyclerViewAdapter(list) {
    private var mList: List<T> = ArrayList()
    override fun onBindViewHolder(
        binding: ViewDataBinding?,
        holder: BaseViewHolder?,
        type: Int,
        data: Any?,
        position: Int
    ) {
        if (type == VIEW_TYPE_NORMAL) {
            bindData(binding, holder, data as T?, position)
        }
    }

    /**
     * 绑定数据源
     */
    abstract fun bindData(
        binding: ViewDataBinding?,
        holder: BaseViewHolder?,
        data: T?,
        position: Int
    )

    companion object {
        private const val TAG = "SingleTypeAdapter"
    }

    init {
        mList = list
    }
}