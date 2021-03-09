package com.wdz.module_article.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wdz.common.net.response.TreeResponse
import com.wdz.module_article.system.TreeInfoFragment

public class TreeMainInfoAdapter(fragmentActivity:FragmentActivity, childrenList:MutableList<TreeResponse.Children>): FragmentStateAdapter(fragmentActivity) {
    private var childrenList:MutableList<TreeResponse.Children> = childrenList



    override fun getItemCount(): Int {
        return childrenList.size
    }

    override fun createFragment(position: Int): Fragment {
        return TreeInfoFragment(childrenList[position].id)
    }
}