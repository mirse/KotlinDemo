package com.wdz.ktcommon.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * 通用adapter定义normalItem与emptyItem
 * @author wdz
 */
abstract class BaseBindRecyclerViewAdapter(private var list: List<*>) : RecyclerView.Adapter<BaseBindRecyclerViewAdapter.BaseViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    companion object {
        /**
         * 普通item布局
         */
        const val VIEW_TYPE_NORMAL = 1

        /**
         * 空布局
         */
        const val VIEW_TYPE_EMPTY = 2

        /**
         * 带标题布局
         */
        const val VIEW_TYPE_TITLE = 3

        /**
         * 头布局
         */
        const val VIEW_TYPE_HEAD = 4
    }
    /**
     * 获取layoutId
     *
     * @return
     */
    abstract val layoutId: Int

    /**
     * 获取头布局layoutId
     *
     * @return =0 代表没有HeadView
     */
    abstract val headLayoutId: Int

    /**
     * 获取空布局layoutId
     *
     * @return =0 代表没有emptyView
     */
    abstract val emptyLayoutId: Int

    /**
     * 将数据与view绑定
     *
     * @param type
     * @param data
     * @param position 点击位置
     */
    abstract fun onBindViewHolder(
        binding: ViewDataBinding,
        viewHolder: BaseViewHolder,
        type: Int,
        data: Any,
        position: Int
    )

    override fun getItemViewType(position: Int): Int {
        if (list.isEmpty() && emptyLayoutId != 0) {
            return VIEW_TYPE_EMPTY
        }
        return if (position == 0 && headLayoutId != 0) { VIEW_TYPE_HEAD } else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        var viewDataBinding: ViewDataBinding? = null
        when (viewType) {
            VIEW_TYPE_EMPTY -> {
                viewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    emptyLayoutId,
                    parent,
                    false
                )
            }
            VIEW_TYPE_HEAD -> {
                viewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    headLayoutId,
                    parent,
                    false
                )
            }
            VIEW_TYPE_NORMAL -> {
                viewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )
            }
        }
        return if (viewDataBinding == null) BaseViewHolder(null) else BaseViewHolder(viewDataBinding.root)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        val type = getItemViewType(position)
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
        if (type != VIEW_TYPE_EMPTY && type != VIEW_TYPE_HEAD) {
            val realPosition = getRealPosition(position)
            val data = list[realPosition]

            data?.run {
                binding?.run {
                    onBindViewHolder(binding, holder, type, data, realPosition)
                }
                //标题不添加点击事件
                if (type != VIEW_TYPE_TITLE) {
                    holder.itemView.setOnClickListener {
                        onItemClickListener?.onClickNormal(data, realPosition)
                    }
                    //全部都加上长按监听
                    holder.itemView.isLongClickable = true
                    holder.itemView.setOnLongClickListener { v ->
                        onItemLongClickListener?.onLongClick(data, realPosition, v)
                        true
                    }
                }
            }

        }
    }

    /**
     * 获取正确点击位置
     * @param position
     * @return
     */
    private fun getRealPosition(position: Int): Int {
        return if (position > 0 && headLayoutId != 0) {
            position - 1
        } else {
            position
        }
    }

    /**
     * 点击事件
     */
    interface OnItemClickListener {
        /**
         * 通用点击事件
         * @param object
         * @param position
         */
        fun onClickNormal(data: Any, position: Int)
    }

    interface OnItemLongClickListener {
        /**
         * 通用长点击事件
         * @param object
         * @param position
         * @param view
         */
        fun onLongClick(
            data: Any,
            position: Int,
            view: View
        )
    }

    /**
     * 设置点击监听
     *
     * @param listener
     */
    fun setOnClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    fun setOnLongClickListener(listener: OnItemLongClickListener?) {
        onItemLongClickListener = listener
    }

    override fun getItemCount(): Int {
        var itemCount = list.size
        if (0 != emptyLayoutId && itemCount == 0) {
            //总数0变为1
            return 1
        } else if (headLayoutId != 0) {
            itemCount += 1
        }
        return itemCount
    }

    open inner class BaseViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        private val views: SparseArray<View?> = SparseArray()
        /**
         * 获取id所对应的控件
         *
         * @param id
         * @return
         */
        fun getView(id: Int): View? {
            var view = views[id]
            if (view == null) {
                view = itemView.findViewById(id)
                views.put(id, view)
            }
            return view
        }

    }




}