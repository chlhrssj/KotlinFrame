package com.chlhrssj.basecore.base.ui

import android.content.Context

/**
 * Create by rssj on 2019-12-26
 */
interface ILoadView {

    /**
     * showNormal 页面
     */
    fun showNormal()

    /**
     * Show loading 页面
     */
    fun showLoading()

    /**
     * Show EmptyView 页面
     */
    fun showEmpty()

    /**
     * Show error 页面
     */
    fun showError()

}