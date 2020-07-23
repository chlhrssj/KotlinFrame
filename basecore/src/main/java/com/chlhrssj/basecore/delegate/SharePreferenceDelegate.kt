package com.chlhrssj.basecore.delegate

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.constant.KV_BASESP
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Create by rssj on 2020/7/23
 */
class SharePreferenceDelegate<T> (
    private val key: String,
    private val defaultValue: T,
    private val useCommit: Boolean = false,
    private val spName: String = KV_BASESP,
    private val spMode: Int = Context.MODE_PRIVATE
) : ReadWriteProperty<Any?, T>{

    //延迟属性，只会加载一次
    private val prefs: SharedPreferences by lazy {
        BaseApp.getApp().getSharedPreferences(
            spName,
            spMode
        )
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(key, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(key, value)
    }

    @SuppressLint("ApplySharedPref")
    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
        val editor = when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        if (useCommit) editor.commit() else editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getSharePreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)!!
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Type Error, cannot be got!")
        }
        return res as T
    }

}