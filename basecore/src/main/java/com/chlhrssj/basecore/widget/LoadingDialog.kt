package com.chlhrssj.basecore.widget

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.chlhrssj.basecore.R

/**
 * Create by rssj on 2020-01-13
 */
class LoadingDialog(context: Context, msg: String? = null) {

    var customDialog: CustomDialog
    var process: ProgressBar

    init {
        val view = View.inflate(context, R.layout.dialog_loading, null)
        process = view.findViewById(R.id.iv_anim)
        customDialog = CustomDialog(
            context, view, Gravity.CENTER,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        if (msg != null) {
            val textView: TextView = view.findViewById(R.id.tv_desc)
            textView.text = msg
        }
    }

    fun show() {
        if (!customDialog.isShowing) {
            customDialog.setCancelable(false)
            customDialog.setCanceledOnTouchOutside(false)
            customDialog.show()
        }
    }

    fun hide() {
        CustomDialog.dissDialog(customDialog)
    }

}