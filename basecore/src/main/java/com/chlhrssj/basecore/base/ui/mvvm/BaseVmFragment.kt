package com.chlhrssj.basecore.base.ui.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.chlhrssj.basecore.base.event.BaseEvent
import com.chlhrssj.basecore.base.ui.ILoadView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Create by rssj on 2020/7/8
 */
abstract class BaseVmFragment<VM : BaseViewModel, T : ViewBinding> : Fragment(), ILoadView {

    protected val mView: ILoadView get() = this
    protected var regEvent: Boolean = false

    protected lateinit var viewModel: VM
    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initVM()
        startObserve()
        if (regEvent) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (regEvent) {
            EventBus.getDefault().unregister(this)
        }
    }

    open fun initVM() {
        getVMClass().let {
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    open fun startObserve() {}

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
    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T


    abstract fun getVMClass(): Class<VM>
}