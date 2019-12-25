package com.chlhrssj.basecore.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.R

/**
 * Create by rssj on 2019-12-25
 */
class ToastUtil private constructor() {

    /**
     * 短时间显示Toast
     *
     * @param context 上下文对象
     * @param message 需要显示的字符串信息
     * @param isShort 是否短时间显示
     */
    fun showShort(context: Context, message: CharSequence, isShort: Boolean = true) {
        if (isShort)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    /**
     * 短时间显示Toast
     *
     * @param context 上下文对象
     * @param message 资源ID
     * @param isShort 是否短时间显示
     */
    fun showShort(context: Context, message: Int, isShort: Boolean = true) {
        if (isShort)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文对象
     * @param message  需要显示的字符串信息
     * @param duration 需要显示的时间，单位毫秒
     */
    fun show(context: Context, message: CharSequence, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文对象
     * @param message  资源ID
     * @param duration 需要显示的时间，单位毫秒
     */
    fun show(context: Context, message: Int, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

    /**
     * 中间弹出的Toast
     *
     * @param msg 显示内容
     */
    fun showCenter(msg: String) {

        val toast = Toast(BaseApp.getApp())
        val view = LayoutInflater.from(BaseApp.getApp()).inflate(R.layout.view_toast_center, null)
        val tvText = view.findViewById(R.id.tv_tips) as TextView
        if (tvText != null) {
            tvText.text = msg
        }
        toast.view = view
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

}