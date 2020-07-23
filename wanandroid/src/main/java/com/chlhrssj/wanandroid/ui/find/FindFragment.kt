package com.chlhrssj.wanandroid.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.FragmentFindBinding
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.chlhrssj.wanandroid.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class FindFragment : BaseVmFragment<FindViewModel, FragmentFindBinding>(), View.OnClickListener {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFindBinding = FragmentFindBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<FindViewModel> = FindViewModel::class.java

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()

    }

    override fun onClick(p0: View?) {

    }

}