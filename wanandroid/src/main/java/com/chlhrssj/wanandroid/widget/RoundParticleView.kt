package com.chlhrssj.wanandroid.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.viewpager2.widget.ViewPager2
import java.util.*

/**
 * Create by rssj on 2020/10/23
 */
class RoundParticleView : View {

    private var startRadius: Float = 200f

    var animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
    private var path = Path()
    private var random = Random()

    //定义一个粒子的集合
    private var particleList = mutableListOf<Particle>()

    //定义画笔
    private var paint = Paint()
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private val pathMeasure = PathMeasure()//路径，用于测量扩散圆某一处的X,Y值
    private var pos = FloatArray(2) //扩散圆上某一点的x,y
    private val tan = FloatArray(2)//扩散圆上某一点切线

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        paint.color = Color.WHITE
        paint.isAntiAlias = true

        animator.duration = 2000
        animator.repeatCount = -1
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            updateParticle(it.animatedValue as Float)
            invalidate()//重绘界面
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        path.addCircle(centerX, centerY, startRadius, Path.Direction.CCW)
        pathMeasure.setPath(path, false) //添加path
        var nextX = 0f
        var speed = 0
        var nextY = 0f
        for (i in 0..300) {
            //按比例测量路径上每一点的值
            pathMeasure.getPosTan(i / 300f * pathMeasure.length, pos, tan)
            nextX = pos[0] + random.nextInt(6) - 3f //X值随机偏移
            nextY = pos[1] + random.nextInt(6) - 3f//Y值随机偏移
            speed = random.nextInt(10) + 5
            particleList.add(
                Particle(nextX, nextY, nextX, nextY, 4f, speed.toFloat(), 100, startRadius)
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        particleList.forEach {
            //设置画笔的透明度
            paint.alpha = it.alpha
            canvas?.drawCircle(it.x, it.y, it.radius, paint)
        }
    }

    private fun updateParticle(value: Float) {
        particleList.forEach { particle ->
            if (particle.offset > particle.maxOffset) {
                particle.offset = startRadius
                particle.speed = (random.nextInt(10) + 5).toFloat()
                particle.x = particle.orgX
                particle.y = particle.orgY
            }
            particle.alpha = ((1f - (particle.offset - startRadius) / (particle.maxOffset - startRadius)) * 225f).toInt()
            particle.x = ((particle.x - centerX) * (particle.offset + particle.speed.toInt())) / particle.offset + centerX
            particle.y = ((particle.y - centerY) * (particle.offset + particle.speed.toInt())) / particle.offset + centerY
            particle.offset += particle.speed.toInt()
        }
    }

    data class Particle(
        var x: Float,
        var y: Float,
        var orgX: Float,
        var orgY: Float,
        var radius: Float,
        var speed: Float,
        var alpha: Int,
        var offset: Float = 0f,
        var maxOffset: Float = 400f
    )

}