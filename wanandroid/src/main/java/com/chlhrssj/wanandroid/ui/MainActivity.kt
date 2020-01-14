package com.chlhrssj.wanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import butterknife.BindView
import com.chlhrssj.basecore.R2
import com.chlhrssj.basecore.base.ui.mvc.BaseMvcActivity
import com.chlhrssj.wanandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseMvcActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var mFragmentList: MutableList<Fragment>
    private var mCurrentFragment: Fragment? = null


    override fun initView() {
        initFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initFragment() {
        mFragmentList = ArrayList<Fragment>()
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
        mFragmentList.add(Fragment())
    }
}
