package com.chlhrssj.wanandroid.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseVcActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mFragmentList: MutableList<Fragment>
    private var mCurrentFragment: Fragment? = null

    override fun initView() {
        initFragment()
        initBottomNavigationView()
    }

    override fun initBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tab_home -> addFragment(R.id.ly_pager, mFragmentList[0])
            R.id.tab_yours -> addFragment(R.id.ly_pager, mFragmentList[1])
            R.id.tab_us -> addFragment(R.id.ly_pager, mFragmentList[2])
        }
        return true
    }

    private fun initFragment() {
        mFragmentList = ArrayList()
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
    }

    private fun initBottomNavigationView() {
        binding.nvView.itemIconTintList = null
        binding.nvView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        binding.nvView.setOnNavigationItemSelectedListener {
            this.onNavigationItemSelected(
                it
            )
        }
        // 预设定进来后,默认显示fragment
        addFragment(R.id.ly_pager, mFragmentList[0])

    }

    /**
     * 显示fragment
     *
     * @param frameLayoutId
     * @param fragment
     */
    @Synchronized
    private fun addFragment(frameLayoutId: Int, fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            val temp = mCurrentFragment
            if (fragment.isAdded) {
                if (temp != null) {
                    transaction.hide(temp).show(fragment)
                } else {
                    transaction.show(fragment)
                }
            } else {
                if (temp != null) {
                    transaction.hide(temp).add(frameLayoutId, fragment)
                } else {
                    transaction.add(frameLayoutId, fragment)
                }
            }
            mCurrentFragment = fragment
            transaction.commit()
        }
    }


}
