package com.chlhrssj.wanandroid.ui.us

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.chlhrssj.wanandroid.databinding.FragmentUsBinding
import com.chlhrssj.wanandroid.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class UsFragment : BaseVmFragment<UsViewModel, FragmentUsBinding>(), View.OnClickListener {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUsBinding = FragmentUsBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<UsViewModel> = UsViewModel::class.java

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()

        bar_view.setNavigationOnClickListener { drawer.openDrawer(GravityCompat.START) }
    }

    override fun onClick(p0: View?) {

    }

}