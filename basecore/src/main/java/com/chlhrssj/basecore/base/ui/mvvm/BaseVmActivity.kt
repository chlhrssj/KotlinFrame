package com.chlhrssj.basecore.base.ui.mvvm

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import com.chlhrssj.basecore.base.event.BaseEvent
import com.chlhrssj.basecore.base.ui.ILoadView
import com.chlhrssj.basecore.constant.BaseApp
import com.gyf.immersionbar.ktx.immersionBar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Create by rssj on 2020/4/2
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity(),
    CoroutineScope by MainScope(), ILoadView {

    protected val mView: ILoadView
        get() = this
    protected var regEvent: Boolean = false

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        ButterKnife.bind(this)
        initVM()
        BaseApp.getApp().getActCtrl().addActivity(this)
        initImmersionBar()
        initView()
        initData()
        if (regEvent) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
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
     * 初始化头部
     */
    open fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(true)
        }
    }

    open fun initVM() {
        getVMClass().let {
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: BaseEvent) {
        onEvent(event)
    }

    protected fun onEvent(event: BaseEvent) {

    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayoutId(): Int

    protected abstract fun getVMClass(): Class<VM>

    protected abstract fun initData()

    /**
     * 初始化view
     */
    protected abstract fun initView()

}
