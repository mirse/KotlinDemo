package com.wdz.module_article.adapter

import android.content.Context
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import com.wdz.common.base.adapter.BaseRecyclerViewAdapter
import com.wdz.common.base.adapter.SingleTypeAdapter
import com.wdz.common.databind.SingleBindTypeAdapter
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.R
import com.wdz.module_article.databinding.ItemRecyclerSystemBinding
import com.wdz.module_article.databinding.ItemRecyclerSystemBindingImpl

/**

 * @Author dezhi.wang

 * @Date 2021/3/8 15:34

 */
class SystemAdapter(
    list: MutableList<TreeResponse>
) : SingleBindTypeAdapter<TreeResponse>(list) {
    override fun getLayoutId(): Int {
        return R.layout.item_recycler_system
    }

    override fun getHeadLayoutId(): Int {
        return 0
    }

    override fun getEmptyLayoutId(): Int {
        return 0
    }



    override fun bindData(
        binding: ViewDataBinding?,
        holder: BaseViewHolder?,
        data: TreeResponse?,
        position: Int
    ) {

        val databinding = binding as? ItemRecyclerSystemBinding
        //val binding = DataBindingUtil.getBinding<RecyclerItemMainArticleBindBinding>(holder.itemView)
        if (databinding != null) {
            databinding.treeResponse = data
        }

        val content = holder?.getView(R.id.tv_content) as TextView
        if (data != null) {
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
    }
}