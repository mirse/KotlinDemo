package com.wdz.main.main.adapter

import android.content.Context
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

 * @Date 2021/1/18 14:05

 */
public class HomeAdapter(val mainArticles:List<MainArticle>, diffCallback:DiffUtil.ItemCallback<MainArticle>)
    : PagedListAdapter<MainArticle, HomeAdapter.MyViewHolder>(diffCallback) {


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
            tvTitle.text = article.title
            tvDate.text = article.niceShareDate
            tvSource.text = article.chapterName
        }


    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }






}