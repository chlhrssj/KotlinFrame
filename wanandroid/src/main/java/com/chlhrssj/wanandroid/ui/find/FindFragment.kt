package com.chlhrssj.wanandroid.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.TabBean
import com.chlhrssj.wanandroid.databinding.FragmentFindBinding
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.chlhrssj.wanandroid.ui.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_find.*
import kotlinx.android.synthetic.main.fragment_home.*

class FindFragment : BaseVmFragment<FindViewModel, FragmentFindBinding>() {

    var tabList :List<TabBean> = ArrayList()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFindBinding = FragmentFindBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<FindViewModel> = FindViewModel::class.java

    override fun initView() {
        binding.run {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab) {
                    if (tabList.isNotEmpty() && tabList.size > tab.position) {
                        getProjectList(true, tabList[tab.position].courseId.toString())
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (tabList.isNotEmpty() && tabList.size > tab.position) {
                        getProjectList(true, tabList[tab.position].courseId.toString())
                    }
                }
            })

            refresh.setEnableRefresh(true)
            refresh.setEnableLoadMore(true)
            refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    val position = tabLayout.selectedTabPosition
                    if (tabList.isNotEmpty() && tabList.size > position) {
                        getProjectList(false, tabList[position].courseId.toString())
                    }
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    val position = tabLayout.selectedTabPosition
                    if (tabList.isNotEmpty() && tabList.size > position) {
                        getProjectList(true, tabList[position].courseId.toString())
                    }
                }

            })
        }

        viewModel.run {
            tabLiveData.observe(this@FindFragment, Observer {
                tabList = it
                for (tab in it) {
                    tabLayout.addTab(tabLayout.newTab().setText(tab.name))
                }
                //加载tab后触发列表更新
                binding.refresh.autoRefresh()
            })
            projectLiveData.observe(this@FindFragment, Observer {

            })
        }

        viewModel.getTab()
    }

    fun getProjectList(isRefresh: Boolean, cid: String) {
        viewModel.getProject(isRefresh, cid)
    }


}