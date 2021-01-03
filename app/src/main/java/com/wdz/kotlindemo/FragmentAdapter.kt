package com.wdz.kotlindemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

public class FragmentAdapter(fragmentActivity:FragmentActivity,fragments:List<Fragment>): FragmentStateAdapter(fragmentActivity) {
    private var fragmentList:List<Fragment> = fragments



    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
}