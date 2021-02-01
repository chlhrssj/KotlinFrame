package com.chlhrssj.wanandroid.ui.more

import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.basecore.ext.startKtxActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityMoreBinding
import com.chlhrssj.wanandroid.ui.more.draw.DrawActivity
import com.chlhrssj.wanandroid.ui.more.particle.WangYiYunActivity
import com.chlhrssj.wanandroid.ui.more.rx.RxActivity
import com.gyf.immersionbar.ktx.immersionBar

/**
 * Create by rssj on 2020/8/21
 */
class MoreActivity : BaseVcActivity<ActivityMoreBinding>() {

    override fun initBinding(): ActivityMoreBinding {
        return ActivityMoreBinding.inflate(layoutInflater)
    }

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initView() {
        binding.run {
            btnDraw.setOnClickListener {
                startKtxActivity<DrawActivity>()
            }
            btnParticle.setOnClickListener {
                startKtxActivity<WangYiYunActivity>()
            }
            btnRx.setOnClickListener {
                startKtxActivity<RxActivity>()
            }

            toolbar.setNavigationOnClickListener { finish() }
        }
    }
}