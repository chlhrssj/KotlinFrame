package com.chlhrssj.wanandroid.ui.browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.databinding.ActivityBrowserBinding
import com.chlhrssj.wanandroid.databinding.ActivityLoginBinding

class BrowserActivity : BaseVcActivity<ActivityBrowserBinding>() {

    override fun initBinding(): ActivityBrowserBinding = ActivityBrowserBinding.inflate(layoutInflater)



}