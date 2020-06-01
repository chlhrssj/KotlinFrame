package com.chlhrssj.wanandroid.ui

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.chlhrssj.basecore.base.ui.BaseObserver
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.basecore.http.H_BASETYPE
import com.chlhrssj.basecore.http.HttpHelper
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.api.ApiService
import com.chlhrssj.wanandroid.bean.HomeListBean
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseVcActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var mFragmentList: MutableList<Fragment>
    private var mCurrentFragment: Fragment? = null


    override fun initView() {
        initFragment()
        initBottomNavigationView()
        getData()
    }

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
        nv_view.setItemIconTintList(null)
        nv_view.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED)
        nv_view.setOnNavigationItemSelectedListener {
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

    fun getData() {
//        addDisposable(HttpHelper.getDefault(H_BASETYPE)
//            .create(ApiService::class.java)
//            .getHomeList("1")
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : BaseObserver<HomeListBean>(mView) {
//                override fun onNext(t: HomeListBean) {
//
//                }
//
//                override fun onError(e: Throwable) {
//                    super.onError(e)
//                }
//            })
//        )
    }

}
