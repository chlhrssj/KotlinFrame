package com.chlhrssj.wanandroid.ui.us

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.BaseRequestOptions
import com.chlhrssj.basecore.base.ui.mvvm.BaseVmFragment
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.FragmentHomeBinding
import com.chlhrssj.wanandroid.databinding.FragmentUsBinding
import com.chlhrssj.wanandroid.ui.home.HomeViewModel
import com.chlhrssj.wanandroid.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_us.*
import luyao.wanandroid.model.prefs.UserPrefs

class UsFragment : BaseVmFragment<UsViewModel, FragmentUsBinding>(), View.OnClickListener {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUsBinding = FragmentUsBinding.inflate(inflater, container, false)

    override fun getVMClass(): Class<UsViewModel> = UsViewModel::class.java

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()

        binding.run {
            btnAbout.setOnClickListener(this@UsFragment)
            btnExit.setOnClickListener(this@UsFragment)
            btnMore.setOnClickListener(this@UsFragment)
            ivHead.setOnClickListener(this@UsFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        checkLogin()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_about -> {
            }
            R.id.btn_exit -> doLogout()
            R.id.btn_more -> {
            }
            R.id.iv_head -> if (!UserPrefs.instance.isLogin()) {doLogin()}
        }
    }

    private fun doLogin() {
        context?.let { LoginActivity.openLoginAct(it) }
    }

    private fun doLogout() {
        UserPrefs.instance.setUser(null)
        checkLogin()
    }

    private fun checkLogin() {
        binding.run {
            if (UserPrefs.instance.isLogin()) {
                btnExit.visibility = View.VISIBLE
                tvName.text = UserPrefs.instance.getUser()?.username
                Glide.with(this@UsFragment.context!!)
                    .load(UserPrefs.instance.getUser()?.icon)
                    .placeholder(R.drawable.icon_normal_default_head)
                    .fallback(R.drawable.icon_normal_default_head)
                    .into(ivHead)
            } else {
                btnExit.visibility = View.GONE
                tvName.text = getString(R.string.us_login)
                Glide.with(this@UsFragment.context!!)
                    .load(R.drawable.icon_normal_default_head)
                    .into(ivHead)
            }
        }
    }

}