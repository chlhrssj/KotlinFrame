package com.chlhrssj.basecore.base.ui

import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.observers.ResourceObserver

/**
 * Create by rssj on 2020-01-03
 */
abstract class BaseObserver<T>(var mILoadView: ILoadView) : ResourceObserver<T>() {

    protected var mErrorMsg: String? = null
    protected var mDialogView: ILoadView? = null
    protected var msg = "正在加载中..."
    protected var isShowError = true
    protected var rlRefreshLayout: SmartRefreshLayout? = null

    constructor(view: ILoadView, isShowHUD: Boolean) : this(view) {
        if (isShowHUD) {
            this.mDialogView = view
        }
    }

    constructor(view: ILoadView, rlRefresh: SmartRefreshLayout) : this(view) {
        this.rlRefreshLayout = rlRefresh
    }

    constructor(view: ILoadView, msg1: String) : this(view) {
        mDialogView = view
        msg = msg1
    }


    /**
     * 执行开始（可选）
     * 它会在subscribe(订阅)刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
     * 它总是在subscribe(订阅)所发生的线程被调用(不合适在主线程加载进度条)
     */
    override fun onStart() {
        super.onStart()
        //显示正在加载中的页面,由子页面实现
//        if (mILoadView != null && rlRefreshLayout == null) {
//            mILoadView.showLoading()
//        }
//
//        if (mDialogView != null) {
//            mDialogView.showHUD(msg)
//        }
//
//        val currentActivity = ActivityUtils.getInstance().currentActivity()
//        if (currentActivity != null && !NetUtils.isNetConnected(currentActivity)) {
//            Toast.makeText(currentActivity, "当前无网络", Toast.LENGTH_SHORT).show()
//            onComplete()
//        }

    }

    /**
     * 执行结果
     */
    override fun onComplete() {
//        L.d("执行结果")
//        if (mDialogView != null) {
//            mDialogView!!.dismissHUD()
//        } else if (rlRefreshLayout != null) {
//            rlRefreshLayout!!.finishRefresh()
//            rlRefreshLayout!!.finishLoadMore()
//        }
//        val x = 2
    }

    /**
     * 执行错误
     * @param e
     */
    override fun onError(e: Throwable) {
        Logger.d(e.printStackTrace().toString())
//        if (mView == null) {
//            return
//        }
//        //是否显示错误页面,由子类去实现
//        mView!!.showError()
//
//        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
//            mView!!.showError(mErrorMsg, "-1")
//        } else if (e is ServerException) {
//            mView!!.showError(e.toString(), "-1")
//        } else if (e is HttpException) {
//            mView!!.showError("网络异常", "-1")
//        } else if (e is ApiException) {
//            if ((e as ApiException).getErrorCode() === ApiException.loginTimeOut) {
//                //登录失效
//            }
//        } else {
//            mView!!.showError("未知错误", "-1")
//
//        }
//        // if (isShowError) {
//        //     mView.showError("","-1");
//        // }
//        onComplete()
    }
}