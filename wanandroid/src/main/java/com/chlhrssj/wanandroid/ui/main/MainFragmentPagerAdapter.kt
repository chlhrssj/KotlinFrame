package com.chlhrssj.wanandroid.ui.main

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Create by rssj on 2020/7/13
 */
class MainFragmentPagerAdapter(@NonNull var fm : FragmentManager, @NonNull var lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return fm.fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fm.getFragment(position)
    }


}