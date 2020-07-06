package com.chlhrssj.basecore.base.ui

import android.content.Context

/**
 * Create by rssj on 2019-12-26
 */
interface ILoadView {

    /**
     * showNormal 页面
     */
    fun <T> showNormal(msg: T? = null)

    /**
     * Show loading 页面
     */
    fun <U> showLoading(msg: U? = null)

    /**
     * Show EmptyView 页面
     */
    fun <P> showEmpty(msg: P? = null)

    /**
     * Show error 页面
     */
    fun <Q> showError(msg: Q? = null)

}