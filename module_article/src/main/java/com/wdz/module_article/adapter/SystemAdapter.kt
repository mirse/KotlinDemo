package com.wdz.module_article.adapter

import android.content.Context
import android.widget.TextView
import com.wdz.common.base.adapter.SingleTypeAdapter
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.R

/**

 * @Author dezhi.wang

 * @Date 2021/3/8 15:34

 */
class SystemAdapter(
    mContext: Context,
    list: MutableList<TreeResponse>
) : SingleTypeAdapter<TreeResponse>(mContext, list) {
    override fun getLayoutId(): Int {
        return R.layout.item_recycler_system
    }

    override fun getHeadLayoutId(): Int {
        return 0
    }

    override fun getEmptyLayoutId(): Int {
        return 0
    }

    override fun bindData(holder: BaseViewHolder?, data: TreeResponse?, position: Int) {
        val title = holder?.getView(R.id.tv_title) as TextView
        val content = holder.getView(R.id.tv_content) as TextView
        if (data != null) {
            title.text = data.name
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