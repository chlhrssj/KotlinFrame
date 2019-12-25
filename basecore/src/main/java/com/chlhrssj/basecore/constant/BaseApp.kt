package com.chlhrssj.basecore.constant

import android.app.Application

/**
 * Create by rssj on 2019-12-25
 */
open class BaseApp : Application() {

    //Activity管理
    protected var mActivityControl: ActivityControl = ActivityControl.instance

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    /**
     * 程序终止的时候执行
     */
    override fun onTerminate() {
        super.onTerminate()
        exitApp()
    }

    /**
     * 低内存的时候执行
     */
    override fun onLowMemory() {
        super.onLowMemory()

    }

    /**
     * 程序在内存清理的时候执行
     */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

    }

    /**
     * 退出应用
     */
    fun exitApp() {
        mActivityControl.finishiAll()
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    fun getActCtrl() : ActivityControl =  mActivityControl

    companion object {
        private lateinit var app: BaseApp
        fun getApp() : BaseApp {
            return app
        }
    }
}