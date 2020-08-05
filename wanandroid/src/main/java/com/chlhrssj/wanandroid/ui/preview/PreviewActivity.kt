package com.chlhrssj.wanandroid.ui.preview

import com.bumptech.glide.Glide
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.constant.KV_URL
import com.chlhrssj.wanandroid.databinding.ActivityPreviewBinding
import com.gyf.immersionbar.ktx.immersionBar

class PreviewActivity : BaseVcActivity<ActivityPreviewBinding>() {

    lateinit var url: String

    override fun initBinding(): ActivityPreviewBinding =
        ActivityPreviewBinding.inflate(layoutInflater)

    override fun initData() {
//        url = intent.getStringExtra(KV_URL)
        url = "https://imgs.aixifan.com/content/2020_8_5/1.5965886683495498E9.jpeg"
    }

    override fun initView() {
        binding.run {
            Glide.with(this@PreviewActivity)
                .load(url)
                .into(pvImg)
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }
}