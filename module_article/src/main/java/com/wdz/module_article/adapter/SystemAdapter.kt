package com.wdz.module_article.adapter

import android.content.Context
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.wdz.ktcommon.adapter.SingleBindTypeAdapter
import com.wdz.ktcommon.response.TreeResponse

import com.wdz.module_article.R
import com.wdz.module_article.databinding.ItemRecyclerSystemBinding


/**

 * @Author dezhi.wang

 * @Date 2021/3/8 15:34

 */
class SystemAdapter(
    list: MutableList<TreeResponse>
) : SingleBindTypeAdapter<TreeResponse>(list) {

    override fun bindData(
        binding: ViewDataBinding,
        holder: BaseViewHolder,
        data: Any,
        position: Int
    ) {
        val databinding = binding as? ItemRecyclerSystemBinding
        //val binding = DataBindingUtil.getBinding<RecyclerItemMainArticleBindBinding>(holder.itemView)
        data as TreeResponse
        if (databinding != null) {
            databinding.treeResponse = data
        }

        val content = holder.getView(R.id.tv_content) as TextView
        val string = StringBuilder()
        for (i in data.children.indices) {
            // TODO: 2021/3/8 kotlin字符串拼接方法
            if (i != 0){
                string.append("  ${data.children[i].name}")
            }
            else{
                string.append(data.children[i].name)
            }

        }
        content.text = string

    }

    override val layoutId: Int
        get() = R.layout.item_recycler_system
    override val headLayoutId: Int
        get() = 0
    override val emptyLayoutId: Int
        get() = 0
}