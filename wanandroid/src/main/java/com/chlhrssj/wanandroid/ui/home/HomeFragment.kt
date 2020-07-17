package com.chlhrssj.wanandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>(), View.OnClickListener {

    private val dataList = ArrayList<ArticleListBean.Data>()
    private val homeAdapter by lazy { HomeAdapter(dataList) }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        super.initView()

        binding.run {

            barView.setNavigationOnClickListener {
                drawer.openDrawer(GravityCompat.START)
            }

            nvView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_more -> {
                    }
                    R.id.nav_exit -> {
                    }
                    R.id.nav_about -> {
                    }
                }
                true
            }

            rvData.adapter = homeAdapter
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            refresh.setEnableRefresh(true)
            refresh.setEnableLoadMore(true)
            refresh.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    getList(false)
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    getList(true)
                }

            })

        }

        viewModel.run {
            artLiveData.observe(this@HomeFragment, Observer {
                binding.refresh.run {
                    finishLoadMore()
                    finishRefresh()
                }
                if (it.curPage == 1) {
                    dataList.clear()
                }
                dataList.addAll(it.datas)
                homeAdapter.notifyDataSetChanged()
            })
        }

        binding.refresh.autoRefresh()
    }

    override fun onClick(p0: View?) {

    }

    fun getList(isRefresh: Boolean) {
        viewModel.getList(isRefresh)
    }

}