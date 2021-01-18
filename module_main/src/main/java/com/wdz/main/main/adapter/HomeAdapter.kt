package com.wdz.main.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wdz.common.net.response.MainListResponse
import com.wdz.main.R
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 14:05

 */
public class HomeAdapter(val context: Context,val mainArticles:List<MainArticle>, diffCallback:DiffUtil.ItemCallback<MainArticle>)
    : PagedListAdapter<MainArticle, HomeAdapter.MyViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_main_article, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tv_title)
        val tvDate = holder.itemView.findViewById<TextView>(R.id.tv_date)
        val tvSource = holder.itemView.findViewById<TextView>(R.id.tv_source)

        tvTitle.setText(mainArticles.get(position).title)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

//    class ArticleItemCallback:DiffUtil.ItemCallback<MainArticle>(){
//        override fun areItemsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
//            return oldItem.link == newItem.link
//        }
//
//        override fun areContentsTheSame(oldItem: MainArticle, newItem: MainArticle): Boolean {
//            return oldItem == newItem
//        }
//
//    }




}