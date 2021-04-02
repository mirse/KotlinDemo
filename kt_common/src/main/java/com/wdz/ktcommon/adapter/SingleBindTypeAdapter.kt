package com.wdz.ktcommon.adapter

import androidx.databinding.ViewDataBinding

abstract class SingleBindTypeAdapter<T>(mList: List<T>) : BaseBindRecyclerViewAdapter(mList) {
    private val TAG = this::class.simpleName

    override fun onBindViewHolder(
        binding: ViewDataBinding,
        viewHolder: BaseViewHolder,
        type: Int,
        data: Any,
        position: Int
    ) {
        if (type == VIEW_TYPE_NORMAL) {
            bindData(binding, viewHolder, data, position)
        }
    }

    /**
     * 绑定数据源
     */
    abstract fun bindData(
        binding: ViewDataBinding,
        holder: BaseViewHolder,
        data: Any,
        position: Int
    )

}