package com.chlhrssj.wanandroid.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityMainBinding
import com.chlhrssj.wanandroid.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationView
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseVcActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var mFragmentList: MutableList<Fragment>

    override fun initView() {
        initFragment()

        binding.vpView.adapter = MainFragmentPagerAdapter(this)
        binding.vpView.offscreenPageLimit = 3
        binding.vpView.isUserInputEnabled = false
//        binding.vpView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                binding.nvBView.menu.getItem(position).isChecked = true
//            }
//        })

        initBottomNavigationView()
    }

    override fun initBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tab_home -> vp_view.setCurrentItem(0, true)
            R.id.tab_yours -> vp_view.setCurrentItem(1, true)
            R.id.tab_us -> vp_view.setCurrentItem(2, true)
        }
        return true
    }

    private fun initFragment() {
        mFragmentList = ArrayList()
        mFragmentList.add(HomeFragment())
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
    }

    private fun initBottomNavigationView() {
        binding.nvBView.itemIconTintList = null
        binding.nvBView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        binding.nvBView.setOnNavigationItemSelectedListener {
            this.onNavigationItemSelected(
                it
            )
        }
    }

}
