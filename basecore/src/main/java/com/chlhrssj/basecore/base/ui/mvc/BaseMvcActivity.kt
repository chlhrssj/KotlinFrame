package com.chlhrssj.basecore.base.ui.mvc

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.chlhrssj.basecore.base.impl.ILoadView
import com.chlhrssj.basecore.constant.BaseApp
import com.gyf.immersionbar.ImmersionBar

/**
 * Create by rssj on 2019-12-26
 */
abstract class BaseMvcActivity : AppCompatActivity(), ILoadView {

    protected lateinit var mImmersionBar: ImmersionBar
    protected val mView: ILoadView
        get() = this
    private var unBinder: Unbinder? = null
    protected var regEvent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(getLayoutId())
        unBinder = ButterKnife.bind(this)

        BaseApp.getApp().getActCtrl().addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unBinder?.unbind()

        BaseApp.getApp().getActCtrl().removeActivity(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun showError(msg: String, code: String) {

    }

    override fun showLoadDialog(msg: String) {

    }

    override fun dismissLoadDialog() {

    }

    override fun showNormal() {

    }

    override fun showLoading() {

    }

    override fun showEmpty() {

    }

    override fun showError() {

    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化标题
     */
    protected abstract fun initTitle()

    /**
     * 初始化数据
     */
    protected abstract fun initView()

}