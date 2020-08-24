package com.chlhrssj.wanandroid.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Create by rssj on 2020/8/21
 */
class DrawingBoard : View{

    constructor(context: Context): super(context) {}

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {}

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr){}

    var paint: Paint = Paint()
    var path: Path = Path()

    init {
        paint.run {
            strokeWidth = 5f
            color = Color.RED
            style = Paint.Style.STROKE
            setOnTouchListener(object : View.OnTouchListener {

                override fun onTouch(p0: View, p1: MotionEvent): Boolean {
                    when (p1.action) {
                        MotionEvent.ACTION_DOWN -> {
                            path.moveTo(p1.x, p1.y)
                            invalidate()
                        }
                        MotionEvent.ACTION_MOVE -> {
                            path.lineTo(p1.x, p1.y)
                            invalidate()
                        }
                        MotionEvent.ACTION_UP -> {
                            path.lineTo(p1.x, p1.y)
                            invalidate()
                        }
                        else -> {
                        }
                    }
                    return true
                }

            })
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        canvas.drawPath(path, paint)
    }


}