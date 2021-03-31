package com.wdz.main.main.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding

import com.wdz.ktcommon.adapter.SingleBindTypeAdapter
import com.wdz.main.R
import com.wdz.main.databinding.RecyclerItemMainArticleBindBinding
import com.wdz.main.main.HomeViewModel
import com.wdz.main.main.bean.MainArticle

/**

 * @Author dezhi.wang

 * @Date 2021/3/23 16:08

 */
class MyHomeAdapter(
    list: MutableList<MainArticle>,
    private val vm: HomeViewModel?
) : SingleBindTypeAdapter<MainArticle>(list) {
    override fun bindData(
        binding: ViewDataBinding?,
        holder: BaseViewHolder?,
        data: MainArticle?,
        position: Int
    ) {
        if (holder!=null){
            // TODO: 2021/3/25 两种方式在adapter中获取dataBinding
            //1、基类回传ViewDataBinding转换 2、使用DataBindingUtil.getBinding()
            val databinding = binding as? RecyclerItemMainArticleBindBinding
            //val binding = DataBindingUtil.getBinding<RecyclerItemMainArticleBindBinding>(holder.itemView)
            if (databinding != null) {
                databinding.mainArticle = data
                databinding.model = vm
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.recycler_item_main_article_bind
    override val headLayoutId: Int
        get() = 0
    override val emptyLayoutId: Int
        get() = 0


}