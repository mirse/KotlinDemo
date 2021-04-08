package com.wdz.module_article.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.wdz.module_article.system.TreeInfoFragment

// TODO: 2021/3/18 FragmentPagerAdapter(Fragment对象会一直存在内存中) - FragmentStateAdapter
/**
 * @Author dezhi.wang
 * @Date 2021/3/19 14:37
 */
class ViewPager2Adapter(fragmentManager: FragmentManager,lifecycle:Lifecycle,private val type:Int,private val childrenList:MutableList<Int>): FragmentStateAdapter(
    fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return childrenList.size
    }

    override fun createFragment(position: Int): Fragment {
        return TreeInfoFragment(childrenList[position], type)
    }
}