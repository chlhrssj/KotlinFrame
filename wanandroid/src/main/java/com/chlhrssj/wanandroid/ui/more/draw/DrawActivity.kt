package com.chlhrssj.wanandroid.ui.more.draw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityDrawBinding
import com.chlhrssj.wanandroid.databinding.ActivityMoreBinding
import com.gyf.immersionbar.ktx.immersionBar

class DrawActivity : BaseVcActivity<ActivityDrawBinding>() {

    override fun initBinding(): ActivityDrawBinding = ActivityDrawBinding.inflate(layoutInflater)

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initView() {
        binding.run {
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

}