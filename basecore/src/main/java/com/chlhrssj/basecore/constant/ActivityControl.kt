package com.chlhrssj.basecore.constant

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.HashSet

/**
 * Create by rssj on 2019-12-25
 */
class ActivityControl {

    companion object {
        val instance: ActivityControl by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityControl()
        }
    }

    private var allActivities: MutableSet<Activity> = HashSet()
    private var currentAtivity: WeakReference<Activity>? = null

    /**
     * 描述      :  设置当前运行的Activity。
     * 方法名    :  setCurrentAtivity
     * param    :  currentAtivity 设置当前运行的Activity
     */
    fun setCurrentAtivity(currentAtivity: Activity) {
        this.currentAtivity = WeakReference(currentAtivity)
    }

    /**
     * 描述      :  获取当前运行的Activity,有可能返回null
     * 方法名    :  getCurrentAtivity
     * param    : 无
     * throws
     */
    fun getCurrentAtivity(): Activity? {
        return currentAtivity?.get()
    }

    /**
     * 描述      : 添加Activity到管理
     * 方法名    :  addActivity
     * param    :   act Activity
     */
    fun addActivity(act: Activity) {
        allActivities.add(act)
    }

    /**
     * 描述      :从管理器移除Activity，一般在Ondestroy移除，防止强引用内存泄漏
     * 方法名    :  removeActivity
     * param    :   act Activity
     */
    fun removeActivity(act: Activity) {
        allActivities.remove(act)
    }


    /**
     * 描述      :关闭所有Activity
     * 方法名    :  finishiAll
     * param    :无
     */
    fun finishiAll() {
        for (act in allActivities) {
            act.finish()
        }
    }


    /**
     * 描述      :关闭所有Activity 除了对应的activity
     * 方法名    :  finishiAll
     * param    :无
     */
    fun finishiAllExcept(activity: Activity) {
        for (act in allActivities) {
            if (act !== activity) {
                act.finish()
            }
        }
    }

}