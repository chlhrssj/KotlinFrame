package com.chlhrssj.wanandroid.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityLoginBinding

class LoginActivity : BaseVmActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override fun getVMClass(): Class<LoginViewModel> = LoginViewModel::class.java

}