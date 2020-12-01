package com.chlhrssj.basecore.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.chlhrssj.basecore.R

/**
 * Create by rssj on 2019-09-03
 * 自定义dialog
 */
open class CustomDialog(
    context: Context,
    var view: View,
    var gravity: Int = Gravity.BOTTOM,
    var lw: Int = WindowManager.LayoutParams.MATCH_PARENT,
    var lh: Int = WindowManager.LayoutParams.WRAP_CONTENT
) : Dialog(context, R.style.RssjDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(view)//这行一定要写在前面
        val window = this.window
        window?.setGravity(gravity)
        val params = window?.attributes
        params?.width = lw
        params?.height = lh
        window?.attributes = params
    }

    override fun dismiss() {
        val view = currentFocus
        if (view is TextView) {
            val mInputMethodManager = getContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }

        super.dismiss()
    }

    /**
     * 设置动画
     */
    fun setWindowAnimation(animRes: Int) {
        val window = window
        window?.setWindowAnimations(animRes)
    }

    companion object {

        fun dissDialog(dialogView: CustomDialog?) {
            if (dialogView != null && dialogView.isShowing) {
                dialogView.dismiss()
            }
        }
    }

}