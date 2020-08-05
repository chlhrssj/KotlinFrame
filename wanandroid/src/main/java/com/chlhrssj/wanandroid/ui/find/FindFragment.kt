package com.chlhrssj.wanandroid.ui.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.basecore.ext.startKtxActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.ProjectBean
import com.chlhrssj.wanandroid.bean.TabBean
import com.chlhrssj.wanandroid.constant.KV_URL
import com.chlhrssj.wanandroid.databinding.FragmentFindBinding
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.chlhrssj.wanandroid.ui.browser.BrowserActivity
import com.chlhrssj.wanandroid.ui.home.HomeAdapter
import com.chlhrssj.wanandroid.ui.home.HomeViewModel
import com.chlhrssj.wanandroid.ui.preview.PreviewActivity
import com.google.android.material.tabs.TabLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_find.*
import kotlinx.android.synthetic.main.fragment_home.*

class FindFragment : BaseVmFragment<FindViewModel, FragmentFindBinding>() {

    private var tabList: List<TabBean> = ArrayList()
    private val dataList = ArrayList<ProjectBean.Data>()
    private val findAdapter by lazy { FindAdapter(dataList) }

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
                        binding.refresh.autoRefresh()

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (tabList.isNotEmpty() && tabList.size > tab.position) {
                        binding.refresh.autoRefresh()
                    }
                }
            })

            findAdapter.let {
                it.setOnItemClickListener { _, _, position ->
                    startKtxActivity<BrowserActivity>(
                        value = Pair(
                            KV_URL,
                            dataList[position].projectLink
                        )
                    )
                }
                it.setOnItemChildClickListener { _, view, position ->
                    when (view.id) {
                        R.id.iv_img -> startKtxActivity<PreviewActivity>(value = Pair(KV_URL, dataList[position].projectLink))
                    }
                }
            }

            rvData.adapter = findAdapter
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            refresh.setEnableRefresh(true)
            refresh.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    val position = tabLayout.selectedTabPosition
                    if (tabList.isNotEmpty() && tabList.size > position) {
                        getProjectList(false, tabList[position].id.toString())
                    }
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    val position = tabLayout.selectedTabPosition
                    if (tabList.isNotEmpty() && tabList.size > position) {
                        getProjectList(true, tabList[position].id.toString())
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
            })
            projectLiveData.observe(this@FindFragment, Observer {
                binding.refresh.run {
                    finishLoadMore()
                    finishRefresh()
                }
                if (it.curPage == 1) {
                    dataList.clear()
                    binding.rvData.layoutManager?.scrollToPosition(0)
                }
                if (it.curPage >= it.pageCount) {
                    //页面已加载完毕
                    binding.refresh.setEnableLoadMore(false)
                }
                dataList.addAll(it.datas)
                findAdapter.notifyDataSetChanged()

            })
        }

        viewModel.getTab()
    }

    fun getProjectList(isRefresh: Boolean, cid: String) {
        if (isRefresh)
            binding.refresh.setEnableLoadMore(true)
        viewModel.getProject(isRefresh, cid)
    }


}