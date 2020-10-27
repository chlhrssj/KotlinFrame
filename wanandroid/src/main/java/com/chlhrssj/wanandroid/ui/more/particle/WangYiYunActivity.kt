package com.chlhrssj.wanandroid.ui.more.particle

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.bumptech.glide.Glide
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityWangYiYunBinding
import com.gyf.immersionbar.ktx.immersionBar

class WangYiYunActivity : BaseVcActivity<ActivityWangYiYunBinding>() {

    lateinit var rotateAnimator: ObjectAnimator
    var isAnimator: Boolean = false

    override fun initBinding(): ActivityWangYiYunBinding {
        return ActivityWangYiYunBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()

        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }

        rotateAnimator = ObjectAnimator.ofFloat(binding.ivImg, View.ROTATION, 0f, 360f)
        rotateAnimator.duration = 6000
        rotateAnimator.repeatCount = -1
        rotateAnimator.interpolator = LinearInterpolator()

        binding.run {
            btnExec.setOnClickListener {
                isAnimator = !isAnimator
                if (isAnimator) {
                    btnExec.setImageResource(R.drawable.icon_stop)
                    if (rotateAnimator.isRunning) {
                        rpView.animator.resume()
                        rotateAnimator.resume()
                    } else {
                        rotateAnimator.start()
                        rpView.animator.start()
                    }
                } else {
                    btnExec.setImageResource(R.drawable.icon_start)
                    rotateAnimator.pause()
                    rpView.animator.pause()
                }
            }
        }

    }

}