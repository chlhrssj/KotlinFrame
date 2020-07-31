package com.chlhrssj.wanandroid.ui.baout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.basecore.ext.openBrowser
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.constant.KV_MYGITHUB
import com.chlhrssj.wanandroid.constant.KV_REFERENCEGITHUB
import com.chlhrssj.wanandroid.databinding.ActivityAboutBinding
import com.chlhrssj.wanandroid.databinding.ActivityLoginBinding
import com.gyf.immersionbar.ktx.immersionBar
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20
import de.psdev.licensesdialog.model.Notice

class AboutActivity : BaseVcActivity<ActivityAboutBinding>(), View.OnClickListener {

    override fun initBinding(): ActivityAboutBinding = ActivityAboutBinding.inflate(layoutInflater)

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initView() {
        binding.run {
            toolbar.setNavigationOnClickListener { finish() }

            btnReference.setOnClickListener (this@AboutActivity)
            btnLicense.setOnClickListener (this@AboutActivity)
            btnSource.setOnClickListener (this@AboutActivity)

        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_reference -> openBrowser(KV_REFERENCEGITHUB)
            R.id.btn_license -> {
                val license = ApacheSoftwareLicense20()
                val notice = Notice("", KV_MYGITHUB, "", license)
                LicensesDialog.Builder(this)
                    .setNotices(notice)
                    .build()
                    .show()
            }
            R.id.btn_source -> openBrowser(KV_MYGITHUB)

        }
    }

}