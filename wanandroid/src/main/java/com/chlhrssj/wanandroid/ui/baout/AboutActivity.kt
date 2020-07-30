package com.chlhrssj.wanandroid.ui.baout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.databinding.ActivityAboutBinding
import com.chlhrssj.wanandroid.databinding.ActivityLoginBinding

class AboutActivity : BaseVcActivity<ActivityAboutBinding>() {

    override fun initBinding(): ActivityAboutBinding = ActivityAboutBinding.inflate(layoutInflater)

}