package com.chlhrssj.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.basecore.ext.dp2px
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.BannerBean
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.config.BannerConfig
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener

class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>(), View.OnClickListener {

    private val dataList = ArrayList<ArticleListBean.Data>()
    private val homeAdapter by lazy { HomeAdapter(dataList) }
    private val banner by lazy { Banner<BannerBean, BannerImageAdapter<BannerBean>>(context) }

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

            banner.run {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, banner.dp2px(200))
                indicator = CircleIndicator(context)
                addBannerLifecycleObserver(this@HomeFragment)
                setOnBannerListener { _, _ ->

                }
            }

            homeAdapter.let {
                it.addHeaderView(banner)
                it.setOnItemClickListener { _, _, _ ->

                }
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

            bannerLiveData.observe(this@HomeFragment, Observer {
                banner.setAdapter(object : BannerImageAdapter<BannerBean>(it){
                    override fun onBindView(
                        holder: BannerImageHolder?,
                        data: BannerBean?,
                        position: Int,
                        size: Int
                    ) {
                        this@HomeFragment.context?.let { Glide.with(it)
                            .load(data?.imagePath)
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                            .into(holder!!.imageView)}
                    }
                })
                banner.start()
            })
        }

        binding.refresh.autoRefresh()
        viewModel.getBanner()
    }

    override fun onClick(p0: View?) {

    }

    fun getList(isRefresh: Boolean) {
        viewModel.getList(isRefresh)
    }

}

