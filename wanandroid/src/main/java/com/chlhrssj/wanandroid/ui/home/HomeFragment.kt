package com.chlhrssj.wanandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding

class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>() {

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
    }

}