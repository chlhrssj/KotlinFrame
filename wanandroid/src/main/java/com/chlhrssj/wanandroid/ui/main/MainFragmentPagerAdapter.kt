package com.chlhrssj.wanandroid.ui.main

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by rssj on 2020/7/13
 */
class MainFragmentPagerAdapter(@NonNull var act : MainActivity) : FragmentStateAdapter(act) {

    override fun getItemCount(): Int {
        return act.mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return act.mFragmentList[position]
    }

}