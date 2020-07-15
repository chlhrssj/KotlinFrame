package com.chlhrssj.wanandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>(), View.OnClickListener {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()

        binding.barView.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        binding.nvView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_more -> {}
                R.id.nav_exit -> {}
                R.id.nav_about -> {}
            }
            true
        }
    }

    override fun onClick(p0: View?) {

    }



}