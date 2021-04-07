package com.wdz.main.main.adapter

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wdz.main.R
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/2/19 16:13

 */
class SearchInfoAdapter(diffCallback: DiffUtil.ItemCallback<MainArticle>):
PagedListAdapter<MainArticle,SearchInfoAdapter.MyViewHolder>(diffCallback){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_main_article, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //获取数据的方法 paging特定
        val article = getItem(position)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tv_title)
        val tvDate = holder.itemView.findViewById<TextView>(R.id.tv_date)
        val tvSource = holder.itemView.findViewById<TextView>(R.id.tv_source)


        if (article != null) {
            // TODO: 2021/2/20  TextView显示html代码时，Html.fromHtml转换
            //FROM_HTML_MODE_COMPACT：html块元素之间使用一个换行符分隔
            //FROM_HTML_MODE_LEGACY：html块元素之间使用两个换行符分隔
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                tvTitle.text = Html.fromHtml(article.title,FROM_HTML_MODE_COMPACT)
            }
            else {
                tvTitle.text = Html.fromHtml(article.title)
            }

            tvDate.text = article.niceShareDate
            tvSource.text = article.chapterName
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}