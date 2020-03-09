package com.chlhrssj.basecore.base.ui.mvc

import android.content.Context
import androidx.fragment.app.Fragment
import butterknife.Unbinder
import com.chlhrssj.basecore.base.impl.ILoadView
import io.reactivex.disposables.CompositeDisposable

/**
 * Create by rssj on 2020-03-06
 */
class BaseMvcFragment : Fragment(), ILoadView {

    protected val mView: ILoadView
        get() = this
    private var unBinder: Unbinder? = null
    protected var regEvent: Boolean = false

    //管理事件流订阅的生命周期CompositeDisposable
    private var compositeDisposable: CompositeDisposable? = null

    override fun getContext(): Context {
        return context
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
}