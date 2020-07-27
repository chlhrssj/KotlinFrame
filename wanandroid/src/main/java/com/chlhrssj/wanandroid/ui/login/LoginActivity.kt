package com.chlhrssj.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmActivity
import com.chlhrssj.basecore.util.ToastUtils
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityLoginBinding
import com.gyf.immersionbar.ktx.immersionBar
import luyao.wanandroid.model.prefs.UserPrefs

class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    companion object {
        fun openLoginAct(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override fun getVMClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initView() {
        super.initView()

        binding.run {
            toolbar.setNavigationOnClickListener {
                finish()
            }

            btnLogin.setOnClickListener {
                if (canLogin()) doLogin()
            }
        }

    }

    fun canLogin(): Boolean {
        binding.let {
            when {
                it.etName.text.isEmpty() -> {
                    ToastUtils.showShort(this, R.string.toast_null_name)
                }
                it.etPwd.text.isEmpty() -> {
                    ToastUtils.showShort(this, R.string.toast_null_pwd)
                }
                else -> {
                    return true
                }
            }
            return false
        }
    }

    fun doLogin() {
        viewModel.doLogin(
            binding.etName.text.toString(),
            binding.etPwd.text.toString(),
            successBlock = {
                if (UserPrefs.instance.isLogin()) {
                    finish()
                }
            })
    }

}