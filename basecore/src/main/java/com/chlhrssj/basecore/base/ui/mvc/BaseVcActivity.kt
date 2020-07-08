package com.chlhrssj.basecore.base.ui.mvc

import android.content.Context
import android.os.Bundle
import android.view.View.inflate
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import butterknife.ButterKnife
import com.chlhrssj.basecore.base.event.BaseEvent
import com.chlhrssj.basecore.base.ui.ILoadView
import com.chlhrssj.basecore.constant.BaseApp
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Create by rssj on 2019-12-26
 */
abstract class BaseVcActivity<T : ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope(), ILoadView {

    protected val mView: ILoadView
        get() = this
    protected var regEvent: Boolean = false
    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = initBinding()
        setContentView(binding.root)

        BaseApp.getApp().getActCtrl().addActivity(this)
        initImmersionBar()
        initData()
        initView()
        if (regEvent) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BaseApp.getApp().getActCtrl().removeActivity(this)
        if (regEvent) {
            EventBus.getDefault().unregister(this)
        }
        cancel()
    }

    fun getContext(): Context {
        return this
    }

    /**
     * 初始化头部
     */
    open fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(true)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: BaseEvent) {
        onEvent(event)
    }

    open fun onEvent(event: BaseEvent) {}

    override fun showNormal() {}

    override fun showLoading() {}

    override fun showEmpty() {}

    override fun showError() {}

    /**
     * 初始化View
     */
    open fun initView() {}

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化viewbinding
     */
    abstract fun initBinding(): T


}