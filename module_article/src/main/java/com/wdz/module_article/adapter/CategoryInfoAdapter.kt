package com.wdz.module_article.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdz.module_article.R
import com.wdz.module_article.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/1/18 14:05

 */
public class CategoryInfoAdapter(private val fragment: Fragment,diffCallback:DiffUtil.ItemCallback<MainArticle>)
    : PagedListAdapter<MainArticle, CategoryInfoAdapter.MyViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_category_info, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //获取数据的方法 paging特定
        val article = getItem(position)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tv_title)
        val tvDate = holder.itemView.findViewById<TextView>(R.id.tv_date)
        val tvSource = holder.itemView.findViewById<TextView>(R.id.tv_source)
        val ivCover = holder.itemView.findViewById<ImageView>(R.id.iv_cover)
        val tvDes = holder.itemView.findViewById<TextView>(R.id.tv_des)

        if (article != null) {
            tvTitle.text = article.title
            tvDate.text = article.niceShareDate
            tvSource.text = article.chapterName
            tvDes.text = article.des
            Glide.with(fragment)
                .load(article.envelopePic)
                .centerCrop()
                .into(ivCover)
        }


    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }






}