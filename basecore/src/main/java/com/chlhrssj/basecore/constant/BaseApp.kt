package com.chlhrssj.basecore.constant

import android.app.Application
import leakcanary.AppWatcher
import leakcanary.ObjectWatcher
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.addLogAdapter



/**
 * Create by rssj on 2019-12-25
 */
open class BaseApp : Application() {

    //Activity管理
    protected var mActivityControl: ActivityControl = ActivityControl.instance


    override fun onCreate() {
        super.onCreate()
        app = this

        //初始化Logger
        if (KV_LOGABLE) {
            addLogAdapter(AndroidLogAdapter())
        }
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
        @JvmStatic
        private lateinit var app: BaseApp
        @JvmStatic
        fun getApp() : BaseApp {
            return app
        }
    }
}