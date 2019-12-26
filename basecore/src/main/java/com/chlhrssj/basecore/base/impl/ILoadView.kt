package com.chlhrssj.basecore.base.impl

import android.content.Context

/**
 * Create by rssj on 2019-12-26
 */
interface ILoadView {

    fun getContext(): Context

    /**
     * 网络请求错误,弹框提示
     * @param msg
     * @param code
     */
    fun showError(msg: String, code: String)

    /**
     * 显示加载Dialog
     */
    fun showLoadDialog(msg: String)

    /**
     * 关闭加载Dialog
     */
    fun dismissLoadDialog()

    //----------------------------下面用来显示空界面---------------------------//
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